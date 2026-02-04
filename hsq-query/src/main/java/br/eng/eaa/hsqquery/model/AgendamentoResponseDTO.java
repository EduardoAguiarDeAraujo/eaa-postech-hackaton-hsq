package br.eng.eaa.hsqquery.model;

public record AgendamentoResponseDTO(
        String id,
        String unidadeSaude,
        String enderecoUnidadeSaude,
        String especialidadeNome,
        String pacienteNome,
        String status,
        String dataSolicitacao,
        String dataAgemdamento,
        String justificativaMedica
) {
}

