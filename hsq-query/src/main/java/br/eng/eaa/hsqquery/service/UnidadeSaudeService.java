package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.model.UnidadeSaudeDTO;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadeSaudeService {

    private final UnidadeSaudeClient client;
    private final Filter filter;

    public UnidadeSaudeService(UnidadeSaudeClient client, @Qualifier("requestContextFilter") Filter filter) {
        this.client = client;
        this.filter = filter;
    }

    public List<UnidadeSaudeDTO> listarPorCEP(String cep) {
        String cepLimpo = cep.replaceAll("\\D", "");
        String prefixoBusca = cepLimpo.substring(0, 2);

        List<UnidadeSaudeDTO> unidades = client.findAll();

        return unidades.stream()
                .filter(u -> {
                    String cepUnidade = u.endereco().getCep().replaceAll("\\D", "");
                    return cepUnidade.startsWith(prefixoBusca);
                })
                .collect(Collectors.toList());
    }

    public List<UnidadeSaudeDTO> listarPorEspecialidade(String especialidade) {
        return client.listUnidadesSaude(especialidade);
    }

    public List<UnidadeSaudeDTO> findAll() {
        return client.findAll();
    }

    public List<UnidadeSaudeDTO> listarPorEspecialidadeECEP(String especialidade, String cep) {
        String cepLimpo = cep.replaceAll("\\D", "");
        String prefixoBusca = cepLimpo.substring(0, 2);

        List<UnidadeSaudeDTO> unidades = client.listUnidadesSaude(especialidade);

        return unidades.stream()
                .filter(u -> {
                    String cepUnidade = u.endereco().getCep().replaceAll("\\D", "");
                    return cepUnidade.startsWith(prefixoBusca);
                })
                .collect(Collectors.toList());
    }
}
