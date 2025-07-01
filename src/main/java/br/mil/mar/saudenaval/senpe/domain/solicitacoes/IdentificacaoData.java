package br.mil.mar.saudenaval.senpe.domain.solicitacoes;

public class IdentificacaoData {

    private String userID;

    private String username;

    private String om;

    private String tel;

    private String peso;

    private String altura;

    private String dum;

    private String tipo;

    private String local;

    private String aghuse;

    private String detalhes;

    public IdentificacaoData(String userID, String username, String om, String tel, String peso, String altura, String dum, String tipo, String local, String aghuse, String detalhes) {
        this.userID = userID;
        this.username = username;
        this.om = om;
        this.tel = tel;
        this.peso = peso;
        this.altura = altura;
        this.dum = dum;
        this.tipo = tipo;
        this.local = local;
        this.aghuse = aghuse;
        this.detalhes = detalhes;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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
}
