package br.com.zuo.edu.biblioteca.emprestimo;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zuo.edu.biblioteca.exemplar.Exemplar;
import br.com.zuo.edu.biblioteca.usuario.Usuario;

public class EmprestimoRequest {

    @NotNull
    private Long usuarioId;

    @Positive
    @Max(60)
    private Integer prazoEmDias;

    public EmprestimoRequest() {}

    public EmprestimoRequest(Long usuarioId, Integer prazoEmDias) {
        this.usuarioId = usuarioId;
        this.prazoEmDias = prazoEmDias;
    }

    public Emprestimo toModel(Exemplar exemplar, Usuario usuario, Integer prazoEmDias) {
        return new Emprestimo(usuario, exemplar, prazoEmDias);
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Integer getPrazoEmDias() {
        return prazoEmDias;
    }

}
