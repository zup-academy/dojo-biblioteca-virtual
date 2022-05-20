package br.com.zuo.edu.biblioteca.usuario;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario(String nome, LocalDate dataNascimento, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
    }

    @Deprecated
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
