package br.mil.mar.saudenaval.senpe.enums;

import java.util.Arrays;

public enum Status {
    QUALQUER("Qualquer"),
    ENVIADO("Enviado"),
    DESMARCADO("Desmarcado"),
    ANALISE("Analise"),
    AGENDADO("Agendado"),
    PENDENCIA("Pendencia"),
    CANCELADO("Cancelado"),
    NEGADO("Negado"),
    REMOVIDO("Removido");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public static Status getValue(String status){
        return Arrays.stream(values())
                .filter(p-> p.status.equals(status))
                .findFirst().orElse(null);
    }
}
