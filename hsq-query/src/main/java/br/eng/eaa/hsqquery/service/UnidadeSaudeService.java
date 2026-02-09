package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.model.AtendimentoDTO;
import br.eng.eaa.hsqquery.model.SugestaoUnidadeDTO;
import br.eng.eaa.hsqquery.model.UnidadeSaudeDTO;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UnidadeSaudeService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final UnidadeSaudeClient unidadeSaudeClient;
    private final AtendimentoClient atendimentoClient;
    private final Filter filter;

    public UnidadeSaudeService(UnidadeSaudeClient client, AtendimentoClient atendimentoClient, @Qualifier("requestContextFilter") Filter filter) {
        this.unidadeSaudeClient = client;
        this.atendimentoClient = atendimentoClient;
        this.filter = filter;
    }

    public List<UnidadeSaudeDTO> listarPorCEP(String cep) {
        String cepLimpo = cep.replaceAll("\\D", "");
        String prefixoBusca = cepLimpo.substring(0, 2);

        List<UnidadeSaudeDTO> unidades = unidadeSaudeClient.findAll();

        return unidades.stream()
                .filter(u -> {
                    String cepUnidade = u.endereco().getCep().replaceAll("\\D", "");
                    return cepUnidade.startsWith(prefixoBusca);
                })
                .collect(Collectors.toList());
    }

    public List<UnidadeSaudeDTO> listarPorEspecialidade(String especialidade) {
        return unidadeSaudeClient.listUnidadesSaude(especialidade);
    }

    public List<UnidadeSaudeDTO> findAll() {
        return unidadeSaudeClient.findAll();
    }

    public List<UnidadeSaudeDTO> listarPorEspecialidadeECEP(String especialidade, String cep) {
        String cepLimpo = cep.replaceAll("\\D", "");
        String prefixoBusca = cepLimpo.substring(0, 2);

        List<UnidadeSaudeDTO> unidades = unidadeSaudeClient.listUnidadesSaude(especialidade);

        return unidades.stream()
                .filter(u -> {
                    String cepUnidade = u.endereco().getCep().replaceAll("\\D", "");
                    return cepUnidade.startsWith(prefixoBusca);
                })
                .collect(Collectors.toList());
    }

    public List<SugestaoUnidadeDTO> sugestaoUnidadesPorOcupacao(String especialidade, String cep) {

        List<UnidadeSaudeDTO> unidades = listarPorEspecialidadeECEP(especialidade, cep);
        List<AtendimentoDTO> atendimentos = atendimentoClient.listarAtendimentos();
        List<SugestaoUnidadeDTO> sugestoes = new ArrayList<>();

        // Agrupa agendamentos por CNES
        Map<String, List<AtendimentoDTO>> agendamentosPorCnes = atendimentos.stream().collect(Collectors.groupingBy(AtendimentoDTO::cnes));

        for (UnidadeSaudeDTO unidade : unidades) {
            String cnes = unidade.cnes();
            List<AtendimentoDTO> aps = agendamentosPorCnes.getOrDefault(cnes, List.of());

            if (aps.isEmpty()) {
                sugestoes.add(new SugestaoUnidadeDTO(unidade, 0.0, 0.0, 0.0, "Sem agendamentos"));
                continue;
            }

            // === Cálculo dos parâmetros M/M/1 ===
            int quantidade = aps.size();
            double duracaoTotalMin = aps.stream().mapToLong(AtendimentoDTO::duracaoMinutos).sum();
            double tempoMedioServicoMin = duracaoTotalMin / quantidade;

            // Taxa de serviço μ (pacientes por hora)
            double mu = 60.0 / tempoMedioServicoMin;

            // Período de observação (do primeiro início até o último fim real)
            LocalDateTime inicioMaisAntigo = aps.stream()
                    .map(AtendimentoDTO::dataInicio)
                    .min(LocalDateTime::compareTo)
                    .orElse(LocalDateTime.now());

            LocalDateTime fimMaisTarde = aps.stream()
                    .map(a -> a.dataInicio().plusMinutes(a.duracaoMinutos()))
                    .max(LocalDateTime::compareTo)
                    .orElse(inicioMaisAntigo);

            long periodoMinutos = Duration.between(inicioMaisAntigo, fimMaisTarde).toMinutes();
            if (periodoMinutos <= 0) periodoMinutos = 60;

            double periodoHoras = periodoMinutos / 60.0;

            // Taxa de chegada λ (pacientes por hora)
            double lambda = quantidade / periodoHoras;

            // Utilização ρ
            double rho = (mu > 0) ? lambda / mu : 1.0;
            if (rho > 1.0) rho = 1.0;

            // Tempo médio de espera na fila Wq e probabilidade
            double wqMinutos;
            String observacao;

            if (rho >= 1.0 || mu <= lambda) {
                wqMinutos = Double.POSITIVE_INFINITY;
                observacao = "Fila instável (ρ ≥ 1) - Tempo de espera tende a infinito";
            } else {
                // Fórmula M/M/1: Wq = λ / [μ(μ - λ)]
                double wqHoras = lambda / (mu * (mu - lambda));
                wqMinutos = wqHoras * 60.0;
                observacao = "Fila estável";
            }

            double probEspera = rho;

            sugestoes.add(new SugestaoUnidadeDTO(
                    unidade,
                    rho,
                    wqMinutos,
                    probEspera,
                    observacao
            ));
        }

        // Ordena por menor ocupação (ρ)
        sugestoes.sort(Comparator.comparingDouble(SugestaoUnidadeDTO::ocupacaoRho));

        return sugestoes;
    }
}
