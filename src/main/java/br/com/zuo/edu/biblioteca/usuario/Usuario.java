package br.com.zuo.edu.biblioteca.usuario;

import br.com.zuo.edu.biblioteca.livro.Emprestimo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Emprestimo> emprestimos = new ArrayList<>();

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

    public void adicionarEmprestimo(Emprestimo novaReserva) {
        emprestimos.add(novaReserva);
    }
}
