package br.mil.mar.saudenaval.senpe.domain.solicitacoes;

import br.mil.mar.saudenaval.senpe.enums.Status;

public class SolicitacaoData {
    private String id;
    private Status status;
    private String tipo;
    private String protocolo;

    public SolicitacaoData(String id,Status status, String tipo, String protocolo) {
        this.id = id;
        this.status = status;
        this.tipo = tipo;
        this.protocolo = protocolo;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
