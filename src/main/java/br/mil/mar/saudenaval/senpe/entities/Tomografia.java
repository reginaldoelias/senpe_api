package br.mil.mar.saudenaval.senpe.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_tomografia")
public class Tomografia {


    @Id
    private String id;

    @Column(name = "frutos_do_mar")
    private Boolean frutosDoMar;

    private String descricaoFrutosDoMar;

    private Boolean iodo;

    private Boolean medicamentos;

    @Column(name = "descricao_medicamentos")
    private String descricaoMedicamentos;

    private Boolean alimentos;

    @Column(name="descricao_alimentos")
    private String descricaoAlimentos;

    private Boolean asma;

    private LocalDate crise;

    private Boolean cirurgias;

    private String operacoes;

    private Boolean renais;

    @Column(name = "lista_renais")
    private String listRenais;

    private Boolean contrasteVenoso;

    @Column(name = "uso_venoso")
    private String usoVenoso;

    @Column(name = "data_uso_venoso")
    private String dataUsoVenoso;

    private String reacao;

    @Column(name = "tipo_reacao")
    private String tipoReacao;

    private String tavi;

    private String consentimento;

    public Tomografia() {
    }

    public Tomografia(String id, Boolean frutosDoMar, String descricaoFrutosDoMar, Boolean iodo, Boolean medicamentos, String descricaoMedicamentos, Boolean alimentos, String descricaoAlimentos, Boolean asma, LocalDate crise, Boolean cirurgias, String operacoes, Boolean renais, String listRenais, Boolean contrasteVenoso, String usoVenoso, String dataUsoVenoso, String reacao, String tipoReacao, String tavi, String consentimento) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getDescricaoMedicamentos() {
        return descricaoMedicamentos;
    }

    public void setDescricaoMedicamentos(String descricaoMedicamentos) {
        this.descricaoMedicamentos = descricaoMedicamentos;
    }

    public Boolean getFrutosDoMar() {
        return frutosDoMar;
    }

    public void setFrutosDoMar(Boolean frutosDoMar) {
        this.frutosDoMar = frutosDoMar;
    }

    public String getDescricaoFrutosDoMar() {
        return descricaoFrutosDoMar;
    }

    public void setDescricaoFrutosDoMar(String descricaoFrutosDoMar) {
        this.descricaoFrutosDoMar = descricaoFrutosDoMar;
    }

    public Boolean getIodo() {
        return iodo;
    }

    public void setIodo(Boolean iodo) {
        this.iodo = iodo;
    }

    public Boolean getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Boolean medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Boolean getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Boolean alimentos) {
        this.alimentos = alimentos;
    }

    public Boolean getAsma() {
        return asma;
    }

    public void setAsma(Boolean asma) {
        this.asma = asma;
    }

    public LocalDate getCrise() {
        return crise;
    }

    public void setCrise(LocalDate crise) {
        this.crise = crise;
    }

    public Boolean getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(Boolean cirurgias) {
        this.cirurgias = cirurgias;
    }

    public Boolean getRenais() {
        return renais;
    }

    public void setRenais(Boolean renais) {
        this.renais = renais;
    }

    public Boolean getContrasteVenoso() {
        return contrasteVenoso;
    }

    public void setContrasteVenoso(Boolean contrasteVenoso) {
        this.contrasteVenoso = contrasteVenoso;
    }

    public String getDescricaoAlimentos() {
        return descricaoAlimentos;
    }

    public void setDescricaoAlimentos(String descricaoAlimentos) {
        this.descricaoAlimentos = descricaoAlimentos;
    }


    public String getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(String operacoes) {
        this.operacoes = operacoes;
    }


    public String getListRenais() {
        return listRenais;
    }

    public void setListRenais(String listRenais) {
        this.listRenais = listRenais;
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
