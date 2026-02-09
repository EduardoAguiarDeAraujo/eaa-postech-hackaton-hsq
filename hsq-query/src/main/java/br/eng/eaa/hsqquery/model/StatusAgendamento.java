package br.eng.eaa.hsqquery.model;

public enum StatusAgendamento {
    FILA_ESPERA,
    AGENDADO,
    CANCELADO,
    REALIZADO,
    PENDENTE,
    ABSENTEISMO // Quando o paciente falta
}