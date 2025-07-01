package br.mil.mar.saudenaval.senpe.domain.solicitacoes;

import java.time.LocalDate;

public class TomografiaData {

    private String frutosDoMar;

    private String descricaoFrutosDoMar;

    private String iodo;

    private String medicamentos;

    private String descricaoMedicamentos;

    private String alimentos;

    private String descricaoAlimentos;

    private String asma;

    private LocalDate crise;

    private String cirurgias;

    private String operacoes;

    private String renais;

    private String listRenais;

    private String contrasteVenoso;

    private String usoVenoso;

    private String dataUsoVenoso;

    private String reacao;

    private String tipoReacao;

    private String tavi;

    private String consentimento;

    public TomografiaData(String frutosDoMar, String descricaoFrutosDoMar, String iodo, String medicamentos, String descricaoMedicamentos, String alimentos, String descricaoAlimentos, String asma, LocalDate crise, String cirurgias, String operacoes, String renais, String listRenais, String contrasteVenoso, String usoVenoso, String dataUsoVenoso, String reacao, String tipoReacao, String tavi, String consentimento) {
        this.frutosDoMar = frutosDoMar;
        this.descricaoFrutosDoMar = descricaoFrutosDoMar;
        this.iodo = iodo;
        this.medicamentos = medicamentos;
        this.descricaoMedicamentos = descricaoMedicamentos;
        this.alimentos = alimentos;
        this.descricaoAlimentos = descricaoAlimentos;
        this.asma = asma;
        this.crise = crise;
        this.cirurgias = cirurgias;
        this.operacoes = operacoes;
        this.renais = renais;
        this.listRenais = listRenais;
        this.contrasteVenoso = contrasteVenoso;
        this.usoVenoso = usoVenoso;
        this.dataUsoVenoso = dataUsoVenoso;
        this.reacao = reacao;
        this.tipoReacao = tipoReacao;
        this.tavi = tavi;
        this.consentimento = consentimento;
    }

    public String getFrutosDoMar() {
        return frutosDoMar;
    }

    public void setFrutosDoMar(String frutosDoMar) {
        this.frutosDoMar = frutosDoMar;
    }

    public String getDescricaoFrutosDoMar() {
        return descricaoFrutosDoMar;
    }

    public void setDescricaoFrutosDoMar(String descricaoFrutosDoMar) {
        this.descricaoFrutosDoMar = descricaoFrutosDoMar;
    }

    public String getIodo() {
        return iodo;
    }

    public void setIodo(String iodo) {
        this.iodo = iodo;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getDescricaoMedicamentos() {
        return descricaoMedicamentos;
    }

    public void setDescricaoMedicamentos(String descricaoMedicamentos) {
        this.descricaoMedicamentos = descricaoMedicamentos;
    }

    public String getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(String alimentos) {
        this.alimentos = alimentos;
    }

    public String getDescricaoAlimentos() {
        return descricaoAlimentos;
    }

    public void setDescricaoAlimentos(String descricaoAlimentos) {
        this.descricaoAlimentos = descricaoAlimentos;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public LocalDate getCrise() {
        return crise;
    }

    public void setCrise(LocalDate crise) {
        this.crise = crise;
    }

    public String getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(String cirurgias) {
        this.cirurgias = cirurgias;
    }

    public String getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(String operacoes) {
        this.operacoes = operacoes;
    }

    public String getRenais() {
        return renais;
    }

    public void setRenais(String renais) {
        this.renais = renais;
    }

    public String getListRenais() {
        return listRenais;
    }

    public void setListRenais(String listRenais) {
        this.listRenais = listRenais;
    }

    public String getContrasteVenoso() {
        return contrasteVenoso;
    }

    public void setContrasteVenoso(String contrasteVenoso) {
        this.contrasteVenoso = contrasteVenoso;
    }

    public String getUsoVenoso() {
        return usoVenoso;
    }

    public void setUsoVenoso(String usoVenoso) {
        this.usoVenoso = usoVenoso;
    }

    public String getDataUsoVenoso() {
        return dataUsoVenoso;
    }

    public void setDataUsoVenoso(String dataUsoVenoso) {
        this.dataUsoVenoso = dataUsoVenoso;
    }

    public String getReacao() {
        return reacao;
    }

    public void setReacao(String reacao) {
        this.reacao = reacao;
    }

    public String getTipoReacao() {
        return tipoReacao;
    }

    public void setTipoReacao(String tipoReacao) {
        this.tipoReacao = tipoReacao;
    }

    public String getTavi() {
        return tavi;
    }

    public void setTavi(String tavi) {
        this.tavi = tavi;
    }

    public String getConsentimento() {
        return consentimento;
    }

    public void setConsentimento(String consentimento) {
        this.consentimento = consentimento;
    }
}
