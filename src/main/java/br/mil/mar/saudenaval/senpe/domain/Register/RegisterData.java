package br.mil.mar.saudenaval.senpe.domain.Register;

import java.time.LocalDate;

public class RegisterData {
    private String id;
    private String name;
    private String nip;
    private String sexo;
    private String email;
    private LocalDate dataNascimento;
    private String password;
    private String cpf;
    private String perfil;


    public RegisterData() {
    }

    public RegisterData(String id, String name, String nip, String sexo, String email, LocalDate dataNascimento, String password, String cpf, String perfil) {
        this.id = id;
        this.name = name;
        this.nip = nip;
        this.sexo = sexo;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.password = password;
        this.cpf = cpf;
        this.perfil = perfil;
    }

    public String getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
