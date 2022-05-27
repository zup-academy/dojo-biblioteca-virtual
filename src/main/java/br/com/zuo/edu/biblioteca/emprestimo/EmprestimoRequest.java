package br.com.zuo.edu.biblioteca.emprestimo;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zuo.edu.biblioteca.exemplar.Exemplar;
import br.com.zuo.edu.biblioteca.usuario.Usuario;

public class EmprestimoRequest {

    @NotNull
    private Long usuarioId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Future
    private LocalDate dataDevolucao;

    public EmprestimoRequest() {}

    public EmprestimoRequest(Long usuarioId, LocalDate dataDevolucao) {
        this.usuarioId = usuarioId;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo toModel(Exemplar exemplar, Usuario usuario) {
        return new Emprestimo(usuario, exemplar);
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

}
