package br.eng.eaa.hsqquery.model;

import java.time.LocalDateTime;

public record AtendimentoDTO(
        Long id,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        Long duracaoMinutos,
        String cnes,
        String unidadeNome,
        String especialidadeNome
) {
}
