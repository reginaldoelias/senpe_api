package br.mil.mar.saudenaval.senpe.domain.solicitacoes;

import java.time.LocalDate;

public class MarcacaoData {
    private LocalDate dataExame;
    private String exame;
    private String horario;
    private String obs;
    private String operador;
    private String status;

    public MarcacaoData() {
    }

    public MarcacaoData(LocalDate dataExame, String exame, String horario, String obs, String operador, String status) {
        this.dataExame = dataExame;
        this.exame = exame;
        this.horario = horario;
        this.obs = obs;
        this.operador = operador;
        this.status = status;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
