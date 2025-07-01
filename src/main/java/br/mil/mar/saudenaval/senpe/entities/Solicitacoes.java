package br.mil.mar.saudenaval.senpe.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_solicitacoes")
public class Solicitacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "om")
    private String om;

    @Column(name = "tel")
    private String tel;

    @Column(name = "peso")
    private String peso;

    @Column(name = "altura")
    private String altura;

    @Column(name = "dum")
    private String dum;

    @Column(name="tipo")
    private String tipo;

    @Column(name = "unidade")
    private String unidade;

    @Column(name = "file")
    private String file;

    @Column(name = "filename")
    private String filename;

    @Column(name = "exame")
    private String exame;

    @Column(name = "data_exame")
    private LocalDate dataExame;

    @Column(name = "questionario_id")
    private String questionarioId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "nip")
    private String nip;

    @Column(name = "horario")
    private String horario;

    @Column(name = "operador")
    private String operador;

    @Column(name = "profissional")
    private String profissional;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "aghuse")
    private String aghuse;

    @Column(name = "detalhes")
    private String detalhes;

    @Column(name = "prioridade")
    private Boolean prioridade;

    @Column(name = "protocolo")
    private String protocolo;

    @Column(name = "obs")
    private String obs;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Solicitacoes() {
    }

    public Solicitacoes(String id, String om, String tel, String peso, String altura, String dum, String tipo, String unidade, String file, String filename, String exame, LocalDate dataExame, String questionarioId, LocalDateTime createdAt, String userId, String nip, String horario, String operador, String profissional, String situacao, String aghuse, String detalhes, Boolean prioridade, String protocolo, String obs, LocalDateTime updatedAt) {
        this.id = id;
        this.om = om;
        this.tel = tel;
        this.peso = peso;
        this.altura = altura;
        this.dum = dum;
        this.tipo = tipo;
        this.unidade = unidade;
        this.file = file;
        this.filename = filename;
        this.exame = exame;
        this.dataExame = dataExame;
        this.questionarioId = questionarioId;
        this.createdAt = createdAt;
        this.userId = userId;
        this.nip = nip;
        this.horario = horario;
        this.operador = operador;
        this.profissional = profissional;
        this.situacao = situacao;
        this.aghuse = aghuse;
        this.detalhes = detalhes;
        this.prioridade = prioridade;
        this.protocolo = protocolo;
        this.obs = obs;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getOm() {
        return om;
    }

    public void setOm(String om) {
        this.om = om;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getDum() {
        return dum;
    }

    public void setDum(String dum) {
        this.dum = dum;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public String getExame() {
        return exame;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String getQuestionarioId() {
        return questionarioId;
    }

    public void setQuestionarioId(String questionarioId) {
        this.questionarioId = questionarioId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getAghuse() {
        return aghuse;
    }

    public void setAghuse(String aghuse) {
        this.aghuse = aghuse;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Boolean getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Boolean prioridade) {
        this.prioridade = prioridade;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Solicitacoes that = (Solicitacoes) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }
}
