package br.eng.eaa.hsqquery.model;

public record SugestaoUnidadeDTO(
        UnidadeSaudeDTO unidade,
        double ocupacaoRho,           // ρ = λ/μ
        double tempoEsperaFilaMinutos, // Wq em minutos
        double probabilidadeEspera,    // P(Wq > 0) = ρ
        String observacao              // "Fila estável" ou "Fila instável (ρ ≥ 1)"
) {}
