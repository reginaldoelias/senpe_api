package br.mil.mar.saudenaval.senpe.entities;

public class Login {
    private String username;
    private String password;
    private Boolean biometria;

    public Login() {
    }

    public Login(String username, String password, Boolean biometria) {
        this.username = username;
        this.password = password;
        this.biometria = biometria;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBiometria() {
        return biometria;
    }

    public void setBiometria(Boolean biometria) {
        this.biometria = biometria;
    }
}
