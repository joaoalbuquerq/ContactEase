package com.l2code.ContactEase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "coontato", schema = "desafio")
public class Contato implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long id;

    @Column(name = "contato_nome", length = 100)
    @Size(max = 100, message = "O atributo Nome não pode ter mais de 100 caracteres")
    private String nome;

    @Column(name = "contato_email")
    @Size(max = 255, message = "O atributo Email não pode ter mais de 255 caracteres")
    private String email;

    @Column(name = "contato_celular", length = 11)
    @Size(max = 11, message = "Celular não pode ter mais de 11 caracteres")
    private String celular;

    @Column(name = "contato_telefone", length = 10)
    @Size(max = 10, message = "Telefone não pode ter mais de 10 caracteres")
    private String telefone;

    @Column(name = "contato_sn_ativo", length = 1)
    @Size(max = 1, message = "O campo 'ativo' deve ter 1 caractere")
    private String ativo;

    @Column(name = "contato_sn_favorito", length = 1)
    @Size(max = 1, message = "O campo 'favorito' deve ter 1 caractere")
    private String favorito;

    @Column(name = "contato_dh_cad")
    private LocalDateTime dataCadastro;

    @Column(name = "contato_dh_alt")
    private LocalDateTime ultimaAlteracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
}
