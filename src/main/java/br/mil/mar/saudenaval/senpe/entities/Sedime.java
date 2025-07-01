package br.mil.mar.saudenaval.senpe.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_sedime")
public class Sedime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String pi;

    private String descricao;

    private String uf;

    private String preco;


    public Sedime() {
    }

    public String getId() {
        return id;
    }

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sedime sedime = (Sedime) o;
        return Objects.equals(id, sedime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
