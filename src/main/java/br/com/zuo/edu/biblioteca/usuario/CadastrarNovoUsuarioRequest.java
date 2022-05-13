package br.com.zuo.edu.biblioteca.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class CadastrarNovoUsuarioRequest {

    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public CadastrarNovoUsuarioRequest(String nome, TipoUsuario tipoUsuario, LocalDate dataNascimento) {
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.dataNascimento = dataNascimento;
    }

    public CadastrarNovoUsuarioRequest() {
    }

    public Usuario toModel() {
        return new Usuario(nome,dataNascimento,tipoUsuario);
    }

    public String getNome() {
        return nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
