package br.com.zuo.edu.biblioteca.livro;

import javax.validation.constraints.NotNull;

public class SolicitarEmprestimoRequest {

    @NotNull
    private Long idUsuario;

    public SolicitarEmprestimoRequest(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public SolicitarEmprestimoRequest() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
