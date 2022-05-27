package br.com.zuo.edu.biblioteca.exemplar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.zuo.edu.biblioteca.emprestimo.Emprestimo;
import br.com.zuo.edu.biblioteca.livro.Livro;

@Entity
@Table(name = "exemplares")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCirculacao tipoCirculacao;

    @ManyToOne
    private Livro livro;

    @OneToMany(mappedBy = "exemplar", cascade = CascadeType.MERGE)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @Column(nullable = false)
    private boolean disponivel = true;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Exemplar() {

    }

    public Exemplar(TipoCirculacao tipoCirculacao, Livro livro) {
        this.tipoCirculacao = tipoCirculacao;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}