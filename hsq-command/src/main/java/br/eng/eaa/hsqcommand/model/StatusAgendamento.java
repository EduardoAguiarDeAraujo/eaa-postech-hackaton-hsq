package br.eng.eaa.hsqcommand.model;

public enum StatusAgendamento {
    FILA_ESPERA,
    AGENDADO,
    CANCELADO,
    REALIZADO,
    PENDENTE,
    ABSENTEISMO // Quando o paciente falta
}