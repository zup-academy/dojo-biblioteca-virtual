package br.com.zuo.edu.biblioteca.usuario;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioRequest {

    @NotNull
    private TipoUsuario tipoUsuario;

    @NotBlank
    private String nome;

    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public UsuarioRequest() {}

    public UsuarioRequest(@NotNull TipoUsuario tipoUsuario, @NotBlank String nome,
                          @NotNull @PastOrPresent LocalDate dataNascimento) {
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Usuario toModel() {
        return new Usuario(tipoUsuario, nome, dataNascimento);
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

}
