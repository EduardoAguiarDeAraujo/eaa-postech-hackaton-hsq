package br.eng.eaa.hsqcommand.model;

import java.time.LocalDateTime;

public record AgendamentoDTO(
        Long id,
        Long pacienteId,
        Long unidadeSaudeId,
        Long especialidadeId,
        LocalDateTime dataSolicitacao,
        LocalDateTime dataAgemdamento,
        String status,
        String justificativaMedica
) {
}
