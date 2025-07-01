package br.mil.mar.saudenaval.senpe.domain.solicitacoes;

public class ResultadoData {

    private String id;
    private String result;

    public ResultadoData() {
    }

    public ResultadoData(String id, String result) {
        this.id = id;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
