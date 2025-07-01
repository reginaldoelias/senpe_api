package br.mil.mar.saudenaval.senpe.domain.solicitacoes;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class RessonanciaData {

    private String id;

    private String alergia;

    private String descricaoAlergia;

    private String sintomasAlergia;

    private String asma;

    private LocalDate dataCriseAsma;

    private String doenca;

    private String descricaoDoencas;

    private String dor;

    private String localDor;

    private String ladoDor;

    private String protese;

    private String descricaoProtese;

    private String materialProtese;

    private String peniana;

    private String materialPeniana;

    private String arma;

    private String localArma;

    private String implante;

    private String implanteMaterial;

    private String rins;

    private String descricaoRins;

    private String hemodialise;

    private String tatuagem;

    private String insulina;

    private String internacao;

    private LocalDate dataInternacao;

    private String motivoInternacao;

    private String cancer;

    private String localCancer;

    private String familiar;

    private String familiarCancer;

    private String localFamiliarCancer;

    private String febre;

    private String emagrecimento;

    private String passo;

    private String aneurisma;

    private String forca;

    private String formigamento;

    private String localFormigamento;

    private String neuro;

    private String vomito;

    private String convulsao;

    private String tontura;

    private String tremor;

    private String enjoo;

    private String fala;

    private String cabeca;

    private String audicao;

    private String visual;

    private String cirurgia;

    private LocalDate dataCirurgia;

    private String localCirurgia;

    private String quimio;

    private Integer qtdQuimio;

    private LocalDate lastSession;

    private String medicamentos;

    private String descricaoMedicamentos;

    private String contraste;

    private String descricaoContraste;

    private LocalDate dataExameContraste;

    private String reacaoContraste;

    private String sintomasReacaoContraste;

    private String acidente;

    private String infoAcidente;

    private String listAcidente;

    private String biopsiaMama;

    private LocalDate dataBiopsia;

    private String ladoBiopsia;

    private String nodulo;

    private String radioMama;

    private Integer numRadioMama;

    private LocalDate dataRadioMama;

    private String gestante;

    private Integer tempoGestacao;

    private String sangramento;

    private LocalDate dataSangramento;

    private String reposicaoHormonal;

    private String anticoncepcional;

    private String tabelinha;

    private String consentimento;


    public RessonanciaData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getDescricaoAlergia() {
        return descricaoAlergia;
    }

    public void setDescricaoAlergia(String descricaoAlergia) {
        this.descricaoAlergia = descricaoAlergia;
    }

    public String getSintomasAlergia() {
        return sintomasAlergia;
    }

    public void setSintomasAlergia(String sintomasAlergia) {
        this.sintomasAlergia = sintomasAlergia;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public LocalDate getDataCriseAsma() {
        return dataCriseAsma;
    }

    public void setDataCriseAsma(LocalDate dataCriseAsma) {
        this.dataCriseAsma = dataCriseAsma;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getDescricaoDoencas() {
        return descricaoDoencas;
    }

    public void setDescricaoDoencas(String descricaoDoencas) {
        this.descricaoDoencas = descricaoDoencas;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getLocalDor() {
        return localDor;
    }

    public void setLocalDor(String localDor) {
        this.localDor = localDor;
    }

    public String getLadoDor() {
        return ladoDor;
    }

    public void setLadoDor(String ladoDor) {
        this.ladoDor = ladoDor;
    }

    public String getProtese() {
        return protese;
    }

    public void setProtese(String protese) {
        this.protese = protese;
    }

    public String getDescricaoProtese() {
        return descricaoProtese;
    }

    public void setDescricaoProtese(String descricaoProtese) {
        this.descricaoProtese = descricaoProtese;
    }

    public String getMaterialProtese() {
        return materialProtese;
    }

    public void setMaterialProtese(String materialProtese) {
        this.materialProtese = materialProtese;
    }

    public String getPeniana() {
        return peniana;
    }

    public void setPeniana(String peniana) {
        this.peniana = peniana;
    }

    public String getMaterialPeniana() {
        return materialPeniana;
    }

    public void setMaterialPeniana(String materialPeniana) {
        this.materialPeniana = materialPeniana;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public String getLocalArma() {
        return localArma;
    }

    public void setLocalArma(String localArma) {
        this.localArma = localArma;
    }

    public String getImplante() {
        return implante;
    }

    public void setImplante(String implante) {
        this.implante = implante;
    }

    public String getImplanteMaterial() {
        return implanteMaterial;
    }

    public void setImplanteMaterial(String implanteMaterial) {
        this.implanteMaterial = implanteMaterial;
    }

    public String getRins() {
        return rins;
    }

    public void setRins(String rins) {
        this.rins = rins;
    }

    public String getDescricaoRins() {
        return descricaoRins;
    }

    public void setDescricaoRins(String descricaoRins) {
        this.descricaoRins = descricaoRins;
    }

    public String getHemodialise() {
        return hemodialise;
    }

    public void setHemodialise(String hemodialise) {
        this.hemodialise = hemodialise;
    }

    public String getTatuagem() {
        return tatuagem;
    }

    public void setTatuagem(String tatuagem) {
        this.tatuagem = tatuagem;
    }

    public String getInsulina() {
        return insulina;
    }

    public void setInsulina(String insulina) {
        this.insulina = insulina;
    }

    public String getInternacao() {
        return internacao;
    }

    public void setInternacao(String internacao) {
        this.internacao = internacao;
    }

    public LocalDate getDataInternacao() {
        return dataInternacao;
    }

    public void setDataInternacao(LocalDate dataInternacao) {
        this.dataInternacao = dataInternacao;
    }

    public String getMotivoInternacao() {
        return motivoInternacao;
    }

    public void setMotivoInternacao(String motivoInternacao) {
        this.motivoInternacao = motivoInternacao;
    }

    public String getCancer() {
        return cancer;
    }

    public void setCancer(String cancer) {
        this.cancer = cancer;
    }

    public String getLocalCancer() {
        return localCancer;
    }

    public void setLocalCancer(String localCancer) {
        this.localCancer = localCancer;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getFamiliarCancer() {
        return familiarCancer;
    }

    public void setFamiliarCancer(String familiarCancer) {
        this.familiarCancer = familiarCancer;
    }

    public String getLocalFamiliarCancer() {
        return localFamiliarCancer;
    }

    public void setLocalFamiliarCancer(String localFamiliarCancer) {
        this.localFamiliarCancer = localFamiliarCancer;
    }

    public String getFebre() {
        return febre;
    }

    public void setFebre(String febre) {
        this.febre = febre;
    }

    public String getEmagrecimento() {
        return emagrecimento;
    }

    public void setEmagrecimento(String emagrecimento) {
        this.emagrecimento = emagrecimento;
    }

    public String getPasso() {
        return passo;
    }

    public void setPasso(String passo) {
        this.passo = passo;
    }

    public String getAneurisma() {
        return aneurisma;
    }

    public void setAneurisma(String aneurisma) {
        this.aneurisma = aneurisma;
    }

    public String getForca() {
        return forca;
    }

    public void setForca(String forca) {
        this.forca = forca;
    }

    public String getFormigamento() {
        return formigamento;
    }

    public void setFormigamento(String formigamento) {
        this.formigamento = formigamento;
    }

    public String getLocalFormigamento() {
        return localFormigamento;
    }

    public void setLocalFormigamento(String localFormigamento) {
        this.localFormigamento = localFormigamento;
    }

    public String getNeuro() {
        return neuro;
    }

    public void setNeuro(String neuro) {
        this.neuro = neuro;
    }

    public String getVomito() {
        return vomito;
    }

    public void setVomito(String vomito) {
        this.vomito = vomito;
    }

    public String getConvulsao() {
        return convulsao;
    }

    public void setConvulsao(String convulsao) {
        this.convulsao = convulsao;
    }

    public String getTontura() {
        return tontura;
    }

    public void setTontura(String tontura) {
        this.tontura = tontura;
    }

    public String getTremor() {
        return tremor;
    }

    public void setTremor(String tremor) {
        this.tremor = tremor;
    }

    public String getEnjoo() {
        return enjoo;
    }

    public void setEnjoo(String enjoo) {
        this.enjoo = enjoo;
    }

    public String getFala() {
        return fala;
    }

    public void setFala(String fala) {
        this.fala = fala;
    }

    public String getCabeca() {
        return cabeca;
    }

    public void setCabeca(String cabeca) {
        this.cabeca = cabeca;
    }

    public String getAudicao() {
        return audicao;
    }

    public void setAudicao(String audicao) {
        this.audicao = audicao;
    }

    public String getVisual() {
        return visual;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }

    public String getCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(String cirurgia) {
        this.cirurgia = cirurgia;
    }

    public LocalDate getDataCirurgia() {
        return dataCirurgia;
    }

    public void setDataCirurgia(LocalDate dataCirurgia) {
        this.dataCirurgia = dataCirurgia;
    }

    public String getLocalCirurgia() {
        return localCirurgia;
    }

    public void setLocalCirurgia(String localCirurgia) {
        this.localCirurgia = localCirurgia;
    }

    public String getQuimio() {
        return quimio;
    }

    public void setQuimio(String quimio) {
        this.quimio = quimio;
    }

    public Integer getQtdQuimio() {
        return qtdQuimio;
    }

    public void setQtdQuimio(Integer qtdQuimio) {
        this.qtdQuimio = qtdQuimio;
    }

    public LocalDate getLastSession() {
        return lastSession;
    }

    public void setLastSession(LocalDate lastSession) {
        this.lastSession = lastSession;
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

    public String getContraste() {
        return contraste;
    }

    public void setContraste(String contraste) {
        this.contraste = contraste;
    }

    public String getDescricaoContraste() {
        return descricaoContraste;
    }

    public void setDescricaoContraste(String descricaoContraste) {
        this.descricaoContraste = descricaoContraste;
    }

    public LocalDate getDataExameContraste() {
        return dataExameContraste;
    }

    public void setDataExameContraste(LocalDate dataExameContraste) {
        this.dataExameContraste = dataExameContraste;
    }

    public String getReacaoContraste() {
        return reacaoContraste;
    }

    public void setReacaoContraste(String reacaoContraste) {
        this.reacaoContraste = reacaoContraste;
    }

    public String getSintomasReacaoContraste() {
        return sintomasReacaoContraste;
    }

    public void setSintomasReacaoContraste(String sintomasReacaoContraste) {
        this.sintomasReacaoContraste = sintomasReacaoContraste;
    }

    public String getAcidente() {
        return acidente;
    }

    public void setAcidente(String acidente) {
        this.acidente = acidente;
    }

    public String getInfoAcidente() {
        return infoAcidente;
    }

    public void setInfoAcidente(String infoAcidente) {
        this.infoAcidente = infoAcidente;
    }

    public String getListAcidente() {
        return listAcidente;
    }

    public void setListAcidente(String listAcidente) {
        this.listAcidente = listAcidente;
    }

    public String getBiopsiaMama() {
        return biopsiaMama;
    }

    public void setBiopsiaMama(String biopsiaMama) {
        this.biopsiaMama = biopsiaMama;
    }

    public LocalDate getDataBiopsia() {
        return dataBiopsia;
    }

    public void setDataBiopsia(LocalDate dataBiopsia) {
        this.dataBiopsia = dataBiopsia;
    }

    public String getLadoBiopsia() {
        return ladoBiopsia;
    }

    public void setLadoBiopsia(String ladoBiopsia) {
        this.ladoBiopsia = ladoBiopsia;
    }

    public String getNodulo() {
        return nodulo;
    }

    public void setNodulo(String nodulo) {
        this.nodulo = nodulo;
    }

    public String getRadioMama() {
        return radioMama;
    }

    public void setRadioMama(String radioMama) {
        this.radioMama = radioMama;
    }

    public Integer getNumRadioMama() {
        return numRadioMama;
    }

    public void setNumRadioMama(Integer numRadioMama) {
        this.numRadioMama = numRadioMama;
    }

    public LocalDate getDataRadioMama() {
        return dataRadioMama;
    }

    public void setDataRadioMama(LocalDate dataRadioMama) {
        this.dataRadioMama = dataRadioMama;
    }

    public String getGestante() {
        return gestante;
    }

    public void setGestante(String gestante) {
        this.gestante = gestante;
    }

    public Integer getTempoGestacao() {
        return tempoGestacao;
    }

    public void setTempoGestacao(Integer tempoGestacao) {
        this.tempoGestacao = tempoGestacao;
    }

    public String getSangramento() {
        return sangramento;
    }

    public void setSangramento(String sangramento) {
        this.sangramento = sangramento;
    }

    public LocalDate getDataSangramento() {
        return dataSangramento;
    }

    public void setDataSangramento(LocalDate dataSangramento) {
        this.dataSangramento = dataSangramento;
    }

    public String getReposicaoHormonal() {
        return reposicaoHormonal;
    }

    public void setReposicaoHormonal(String reposicaoHormonal) {
        this.reposicaoHormonal = reposicaoHormonal;
    }

    public String getAnticoncepcional() {
        return anticoncepcional;
    }

    public void setAnticoncepcional(String anticoncepcional) {
        this.anticoncepcional = anticoncepcional;
    }

    public String getTabelinha() {
        return tabelinha;
    }

    public void setTabelinha(String tabelinha) {
        this.tabelinha = tabelinha;
    }

    public String getConsentimento() {
        return consentimento;
    }

    public void setConsentimento(String consentimento) {
        this.consentimento = consentimento;
    }
}
