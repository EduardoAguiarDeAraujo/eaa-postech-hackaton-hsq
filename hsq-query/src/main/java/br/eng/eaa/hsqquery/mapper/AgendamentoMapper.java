package br.eng.eaa.hsqquery.mapper;

import br.eng.eaa.hsqquery.model.Agendamento;
import br.eng.eaa.hsqquery.model.AgendamentoDTO;
import br.eng.eaa.hsqquery.model.AgendamentoResponseDTO;

import java.time.format.DateTimeFormatter;

public class AgendamentoMapper {

    private AgendamentoMapper() {}

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static AgendamentoDTO toAgendamentoDTO(Agendamento a){
        return new AgendamentoDTO(
                a.getId(),
                a.getPaciente().getId(),
                a.getUnidadeSaude() != null ? a.getUnidadeSaude().getId() : null,
                a.getEspecialidade().getId(),
                a.getDataSolicitacao(),
                a.getDataAgendamento(),
                a.getStatus().name(),
                a.getJustificativaMedica()
        );
    }

    public static AgendamentoResponseDTO toAgendamentoResponseDTO(Agendamento a) {
        String enderecoFormatado = a.getUnidadeSaude() != null && a.getUnidadeSaude().getEndereco() != null
                ? formatarEndereco(a.getUnidadeSaude().getEndereco())
                : "Endereço não informado";

        return new AgendamentoResponseDTO(
                a.getId().toString(),
                a.getUnidadeSaude() != null ? a.getUnidadeSaude().getNomeFantasia() : "Pendente",
                enderecoFormatado,
                a.getEspecialidade().getNome(),
                a.getPaciente().getNome(),
                a.getStatus().name(),
                a.getDataSolicitacao().format(formatter),
                a.getDataAgendamento() != null ? a.getDataAgendamento().format(formatter) : "Aguardando data",
                a.getJustificativaMedica()
        );
    }

    private static String formatarEndereco(br.eng.eaa.hsqquery.model.Endereco e) {
        return String.format("%s, %s - %s, %s/%s",
                e.getLogradouro(), e.getNumero(), e.getBairro(), e.getCidade(), e.getEstado());
    }
}
