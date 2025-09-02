package br.mil.mar.saudenaval.senpe.domain.Register;

public class CodigoDTO {
    private String email;
    private int codigo;

    public CodigoDTO(String email, int codigo) {
        this.email = email;
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
