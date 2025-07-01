package br.mil.mar.saudenaval.senpe.domain;

public class UserSsm {

    private String name;
    private String nip;
    private String cpf;
    private String email;
    private String tel;

    public UserSsm() {
    }

    public UserSsm(String name, String nip, String cpf, String email, String tel) {
        this.name = name;
        this.nip = nip;
        this.cpf = cpf;
        this.email = email;
        this.tel = tel;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


}
