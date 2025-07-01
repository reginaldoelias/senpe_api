package br.mil.mar.saudenaval.senpe.entities;


import br.mil.mar.saudenaval.senpe.enums.Perfil;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "tb_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nip_tit")
    private String nipTitular;

    @Column
    private String posto;

    @Column(name = "espec")
    private String especialidade;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    @Column
    private String sexo;

    @Column(name = "nip_vinc")
    private String nipVinculado;

    @Column(name = "tel")
    private String tel;

    @Column
    private String pai;

    @Column
    private String mae;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String cpf;

    @Column
    private String password;


    @Column
    private int failedAttempt = 0;

    @Column
    private boolean accountNonLocked;

    @Column
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;


    @Column(name = "login_at")
    private LocalDateTime lastLogin;

    @Column(name="login_count")
    private int loginCount;

    @Column(name="whatsapp")
    private Boolean whatsapp;

    public User() {
    }

    public User(String nipTitular, String posto, String especialidade, String name, LocalDate dataNascimento, String sexo, String nipVinculado, String tel, String pai, String mae, String email, String username, String cpf, String password, int failedAttempt, boolean accountNonLocked, Perfil perfil, LocalDateTime createdAt, LocalDateTime lastLogin, int loginCount,Boolean whatsapp) {
        this.nipTitular = nipTitular;
        this.posto = posto;
        this.especialidade = especialidade;
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nipVinculado = nipVinculado;
        this.tel = tel;
        this.pai = pai;
        this.mae = mae;
        this.email = email;
        this.username = username;
        this.cpf = cpf;
        this.password = password;
        this.failedAttempt = failedAttempt;
        this.accountNonLocked = accountNonLocked;
        this.perfil = perfil;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.loginCount = loginCount;
        this.whatsapp = whatsapp;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public String getNipTitular() {
        return nipTitular;
    }

    public void setNipTitular(String nipTitular) {
        this.nipTitular = nipTitular;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNipVinculado() {
        return nipVinculado;
    }

    public void setNipVinculado(String nipVinculado) {
        this.nipVinculado = nipVinculado;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }


    public Boolean getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        switch (this.perfil){
            case ADMINISTRADOR -> {
                return List.of(new SimpleGrantedAuthority("ROLE_" + Perfil.ADMINISTRADOR.name()));
            }
            case OPERADOR ->  {
                return List.of(new SimpleGrantedAuthority("ROLE_" + Perfil.OPERADOR.name()));
            }
            case PACIENTE -> {
                return List.of(new SimpleGrantedAuthority("ROLE_" + Perfil.PACIENTE.name()));
            }
            default -> {
                return List.of( new SimpleGrantedAuthority("ROLE_USER"));
            }
        }

        /* if (this.perfil == Perfil.ADMINISTRADOR){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            return List.of( new SimpleGrantedAuthority("ROLE_USER"));
        }*/

    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
