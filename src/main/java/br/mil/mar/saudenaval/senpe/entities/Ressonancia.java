package br.mil.mar.saudenaval.senpe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tb_ressonancia")
public class Ressonancia {

    @Id
    private String id;

    private Boolean alergia;

    @Column(name = "descricao_alergia")
    private String descricaoAlergia;

    @Column(name = "sintomas_alergia")
    private String sintomasAlergia;

    private Boolean asma;

    @Column(name = "data_crise_asma")
    private LocalDate dataCriseAsma;

    private Boolean doenca;

    @Column(name = "descricao_doencas")
    private String descricaoDoencas;

    private Boolean dor;

    @Column(name = "local_dor")
    private String localDor;

    @Column(name = "lado_dor")
    private String ladoDor;

    private Boolean protese;

    @Column(name = "descricao_protese")
    private String descricaoProtese;

    @Column(name = "material_protese")
    private String materialProtese;

    private Boolean peniana;

    @Column(name = "material_peniana")
    private String materialPeniana;

    private Boolean arma;

    @Column(name = "local_arma")
    private String localArma;

    private Boolean implante;

    @Column(name = "implante_material")
    private String implanteMaterial;

    private Boolean rins;

    @Column(name = "descricao_rins")
    private String descricaoRins;

    private Boolean hemodialise;

    private Boolean tatuagem;

    private Boolean insulina;

    private Boolean internacao;

    @Column(name = "data_internacao")
    private LocalDate dataInternacao;

    @Column(name = "motivo_internacao")
    private String motivoInternacao;

    private Boolean cancer;

    @Column(name = "local_cancer")
    private String localCancer;

    private Boolean familiar;

    @Column(name = "familiar_cancer")
    private String familiarCancer;

    @Column(name = "local_familiar_cancer")
    private String localFamiliarCancer;

    private Boolean febre;

    private Boolean emagrecimento;

    private Boolean passo;

    private Boolean aneurisma;

    private Boolean forca;

    private Boolean formigamento;

    @Column(name = "local_formigamento")
    private String localFormigamento;

    private Boolean neuro;

    private Boolean vomito;

    private Boolean convulsao;

    private Boolean tontura;

    private Boolean tremor;

    private Boolean enjoo;

    private Boolean fala;

    private Boolean cabeca;

    private Boolean audicao;

    private Boolean visual;

    private Boolean cirurgia;

    @Column(name = "data_cirurgia")
    private LocalDate dataCirurgia;

    @Column(name = "local_cirurgia")
    private String localCirurgia;

    private Boolean quimio;

    @Column(name = "qtd_quimio")
    private Integer qtdQuimio;

    @Column(name = "last_session")
    private LocalDate lastSession;

    private Boolean medicamentos;

    @Column(name = "descricao_medicamentos")
    private String descricaoMedicamentos;

    private Boolean contraste;

    @Column(name = "descricao_contraste")
    private String descricaoContraste;

    @Column(name = "data_exame_contraste")
    private LocalDate dataExameContraste;

    @Column(name = "reacao_contraste")
    private Boolean reacaoContraste;

    @Column(name = "sintomas_reacao_contraste")
    private String sintomasReacaoContraste;

    private Boolean acidente;

    @Column(name = "info_acidente")
    private String infoAcidente;

    @Column(name = "lista_acidente")
    private String listAcidente;

    @Column(name = "biopsia_mama")
    private Boolean biopsiaMama;

    @Column(name = "data_biopsia")
    private LocalDate dataBiopsia;

    @Column(name = "lado_biopsia")
    private String ladoBiopsia;

    private Boolean nodulo;

    @Column(name = "radio_mama")
    private Boolean radioMama;

    @Column(name = "num_radio_mama")
    private Integer numRadioMama;

    @Column(name = "data_radio_mama")
    private LocalDate dataRadioMama;

    private Boolean gestante;

    @Column(name = "tempo_gestacao")
    private Integer tempoGestacao;

    private Boolean sangramento;

    @Column(name = "data_sangramento")
    private LocalDate dataSangramento;

    @Column(name = "reposicao_hormonal")
    private Boolean reposicaoHormonal;

    private Boolean anticoncepcional;

    private Boolean tabelinha;

    private Boolean consentimento;

    public Ressonancia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAlergia() {
        return alergia;
    }

    public void setAlergia(Boolean alergia) {
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

    public Boolean getAsma() {
        return asma;
    }

    public void setAsma(Boolean asma) {
        this.asma = asma;
    }

    public LocalDate getDataCriseAsma() {
        return dataCriseAsma;
    }

    public void setDataCriseAsma(LocalDate dataCriseAsma) {
        this.dataCriseAsma = dataCriseAsma;
    }

    public Boolean getDoenca() {
        return doenca;
    }

    public void setDoenca(Boolean doenca) {
        this.doenca = doenca;
    }

    public String getDescricaoDoencas() {
        return descricaoDoencas;
    }

    public void setDescricaoDoencas(String descricaoDoencas) {
        this.descricaoDoencas = descricaoDoencas;
    }

    public Boolean getDor() {
        return dor;
    }

    public void setDor(Boolean dor) {
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

    public Boolean getProtese() {
        return protese;
    }

    public void setProtese(Boolean protese) {
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

    public Boolean getPeniana() {
        return peniana;
    }

    public void setPeniana(Boolean peniana) {
        this.peniana = peniana;
    }

    public String getMaterialPeniana() {
        return materialPeniana;
    }

    public void setMaterialPeniana(String materialPeniana) {
        this.materialPeniana = materialPeniana;
    }

    public Boolean getArma() {
        return arma;
    }

    public void setArma(Boolean arma) {
        this.arma = arma;
    }

    public String getLocalArma() {
        return localArma;
    }

    public void setLocalArma(String localArma) {
        this.localArma = localArma;
    }

    public Boolean getImplante() {
        return implante;
    }

    public void setImplante(Boolean implante) {
        this.implante = implante;
    }

    public String getImplanteMaterial() {
        return implanteMaterial;
    }

    public void setImplanteMaterial(String implanteMaterial) {
        this.implanteMaterial = implanteMaterial;
    }

    public Boolean getRins() {
        return rins;
    }

    public void setRins(Boolean rins) {
        this.rins = rins;
    }

    public String getDescricaoRins() {
        return descricaoRins;
    }

    public void setDescricaoRins(String descricaoRins) {
        this.descricaoRins = descricaoRins;
    }

    public Boolean getHemodialise() {
        return hemodialise;
    }

    public void setHemodialise(Boolean hemodialise) {
        this.hemodialise = hemodialise;
    }

    public Boolean getTatuagem() {
        return tatuagem;
    }

    public void setTatuagem(Boolean tatuagem) {
        this.tatuagem = tatuagem;
    }

    public Boolean getInsulina() {
        return insulina;
    }

    public void setInsulina(Boolean insulina) {
        this.insulina = insulina;
    }

    public Boolean getInternacao() {
        return internacao;
    }

    public void setInternacao(Boolean internacao) {
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

    public Boolean getCancer() {
        return cancer;
    }

    public void setCancer(Boolean cancer) {
        this.cancer = cancer;
    }

    public String getLocalCancer() {
        return localCancer;
    }

    public void setLocalCancer(String localCancer) {
        this.localCancer = localCancer;
    }

    public Boolean getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Boolean familiar) {
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

    public Boolean getFebre() {
        return febre;
    }

    public void setFebre(Boolean febre) {
        this.febre = febre;
    }

    public Boolean getEmagrecimento() {
        return emagrecimento;
    }

    public void setEmagrecimento(Boolean emagrecimento) {
        this.emagrecimento = emagrecimento;
    }

    public Boolean getPasso() {
        return passo;
    }

    public void setPasso(Boolean passo) {
        this.passo = passo;
    }

    public Boolean getAneurisma() {
        return aneurisma;
    }

    public void setAneurisma(Boolean aneurisma) {
        this.aneurisma = aneurisma;
    }

    public Boolean getForca() {
        return forca;
    }

    public void setForca(Boolean forca) {
        this.forca = forca;
    }

    public Boolean getFormigamento() {
        return formigamento;
    }

    public void setFormigamento(Boolean formigamento) {
        this.formigamento = formigamento;
    }

    public String getLocalFormigamento() {
        return localFormigamento;
    }

    public void setLocalFormigamento(String localFormigamento) {
        this.localFormigamento = localFormigamento;
    }

    public Boolean getNeuro() {
        return neuro;
    }

    public void setNeuro(Boolean neuro) {
        this.neuro = neuro;
    }

    public Boolean getVomito() {
        return vomito;
    }

    public void setVomito(Boolean vomito) {
        this.vomito = vomito;
    }

    public Boolean getConvulsao() {
        return convulsao;
    }

    public void setConvulsao(Boolean convulsao) {
        this.convulsao = convulsao;
    }

    public Boolean getTontura() {
        return tontura;
    }

    public void setTontura(Boolean tontura) {
        this.tontura = tontura;
    }

    public Boolean getTremor() {
        return tremor;
    }

    public void setTremor(Boolean tremor) {
        this.tremor = tremor;
    }

    public Boolean getEnjoo() {
        return enjoo;
    }

    public void setEnjoo(Boolean enjoo) {
        this.enjoo = enjoo;
    }

    public Boolean getFala() {
        return fala;
    }

    public void setFala(Boolean fala) {
        this.fala = fala;
    }

    public Boolean getCabeca() {
        return cabeca;
    }

    public void setCabeca(Boolean cabeca) {
        this.cabeca = cabeca;
    }

    public Boolean getAudicao() {
        return audicao;
    }

    public void setAudicao(Boolean audicao) {
        this.audicao = audicao;
    }

    public Boolean getVisual() {
        return visual;
    }

    public void setVisual(Boolean visual) {
        this.visual = visual;
    }

    public Boolean getCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(Boolean cirurgia) {
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

    public Boolean getQuimio() {
        return quimio;
    }

    public void setQuimio(Boolean quimio) {
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

    public Boolean getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Boolean medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getDescricaoMedicamentos() {
        return descricaoMedicamentos;
    }

    public void setDescricaoMedicamentos(String descricaoMedicamentos) {
        this.descricaoMedicamentos = descricaoMedicamentos;
    }

    public Boolean getContraste() {
        return contraste;
    }

    public void setContraste(Boolean contraste) {
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

    public Boolean getReacaoContraste() {
        return reacaoContraste;
    }

    public void setReacaoContraste(Boolean reacaoContraste) {
        this.reacaoContraste = reacaoContraste;
    }

    public String getSintomasReacaoContraste() {
        return sintomasReacaoContraste;
    }

    public void setSintomasReacaoContraste(String sintomasReacaoContraste) {
        this.sintomasReacaoContraste = sintomasReacaoContraste;
    }

    public Boolean getAcidente() {
        return acidente;
    }

    public void setAcidente(Boolean acidente) {
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

    public Boolean getBiopsiaMama() {
        return biopsiaMama;
    }

    public void setBiopsiaMama(Boolean biopsiaMama) {
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

    public Boolean getNodulo() {
        return nodulo;
    }

    public void setNodulo(Boolean nodulo) {
        this.nodulo = nodulo;
    }

    public Boolean getRadioMama() {
        return radioMama;
    }

    public void setRadioMama(Boolean radioMama) {
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

    public Boolean getGestante() {
        return gestante;
    }

    public void setGestante(Boolean gestante) {
        this.gestante = gestante;
    }

    public Integer getTempoGestacao() {
        return tempoGestacao;
    }

    public void setTempoGestacao(Integer tempoGestacao) {
        this.tempoGestacao = tempoGestacao;
    }

    public Boolean getSangramento() {
        return sangramento;
    }

    public void setSangramento(Boolean sangramento) {
        this.sangramento = sangramento;
    }

    public LocalDate getDataSangramento() {
        return dataSangramento;
    }

    public void setDataSangramento(LocalDate dataSangramento) {
        this.dataSangramento = dataSangramento;
    }

    public Boolean getReposicaoHormonal() {
        return reposicaoHormonal;
    }

    public void setReposicaoHormonal(Boolean reposicaoHormonal) {
        this.reposicaoHormonal = reposicaoHormonal;
    }

    public Boolean getAnticoncepcional() {
        return anticoncepcional;
    }

    public void setAnticoncepcional(Boolean anticoncepcional) {
        this.anticoncepcional = anticoncepcional;
    }

    public Boolean getTabelinha() {
        return tabelinha;
    }

    public void setTabelinha(Boolean tabelinha) {
        this.tabelinha = tabelinha;
    }

    public Boolean getConsentimento() {
        return consentimento;
    }

    public void setConsentimento(Boolean consentimento) {
        this.consentimento = consentimento;
    }
}
