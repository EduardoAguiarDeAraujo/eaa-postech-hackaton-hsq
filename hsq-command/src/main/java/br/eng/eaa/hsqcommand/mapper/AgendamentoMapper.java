package br.eng.eaa.hsqcommand.mapper;

import br.eng.eaa.hsqcommand.model.Agendamento;
import br.eng.eaa.hsqcommand.model.AgendamentoDTO;

public class AgendamentoMapper {

    private AgendamentoMapper() {}

    public static AgendamentoDTO toDTO(Agendamento a){
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
}
