package br.com.zuo.edu.biblioteca.livro;

import br.com.zuo.edu.biblioteca.usuario.TipoUsuario;
import br.com.zuo.edu.biblioteca.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ExemplarLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private TipoCirculacao tipoCirculacao;
    @ManyToOne
    private Livro livro;
    private Boolean reservado = false;

    @OneToMany(mappedBy = "exemplar", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @Deprecated
    public ExemplarLivro() {
    }

    public ExemplarLivro(TipoCirculacao tipoCirculacao, Livro livro) {
        this.tipoCirculacao = tipoCirculacao;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public TipoCirculacao getTipoCirculacao() {
        return tipoCirculacao;
    }

    public Emprestimo solicitarEmprestimo(Usuario usuario, Integer prazoEmDias, Integer quantidadeEmprestimoDoUsuario) {
        boolean usuarioPadrao = usuario.getTipoUsuario().equals(TipoUsuario.PADRAO);
        boolean exemplarRestrito = tipoCirculacao.equals(TipoCirculacao.RESTRITO);

        if(usuarioPadrao && exemplarRestrito) {
            throw new LivroRestritoParaUsuarioPadraoException("Livro restrito para esse usuário");
        }

        if(usuarioPadrao && prazoEmDias == null) {
            throw new PrazoSemDefinicaoParaUsuarioPadraoException("Usuário não definiu o tempo de empréstimo");
        }
        // Data de entrega
        if (prazoEmDias == null) {
            prazoEmDias = 60;
        }

        LocalDate dataDeEntrega = LocalDate.now().plusDays(prazoEmDias);

        // quantidade de emprestimo dos usuários padrão com exemplares reservados
        if (usuarioPadrao && quantidadeEmprestimoDoUsuario > 5) {
            throw new QuantidadeMaximaDeEmprestimoParaUsuarioPadraoException("Usuário já possui número máximo de empréstimos");
        }

        Emprestimo emprestimo = new Emprestimo(this, usuario, dataDeEntrega, prazoEmDias);
        this.reservado = true;
        this.emprestimos.add(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);

        return emprestimo;
    }

    public void devolver() {
        this.reservado = false;
    }
}
