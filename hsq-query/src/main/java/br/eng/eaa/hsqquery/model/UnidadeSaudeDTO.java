package br.eng.eaa.hsqquery.model;

public record UnidadeSaudeDTO(
        String id,
        String cnes,
        String nomeFantasia,
        String razaoSocial,
        Endereco endereco
) {
}
