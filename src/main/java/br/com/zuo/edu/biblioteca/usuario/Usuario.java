package br.com.zuo.edu.biblioteca.usuario;

import br.com.zuo.edu.biblioteca.emprestimo.Emprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos  = new ArrayList<>();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Usuario() {}

    public Usuario(TipoUsuario tipoUsuario, String nome, LocalDate dataNascimento) {
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void adicionar(Emprestimo emprestimo){
        this.emprestimos.add(emprestimo);
    }

}
