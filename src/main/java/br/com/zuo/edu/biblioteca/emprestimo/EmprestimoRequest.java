package br.com.zuo.edu.biblioteca.emprestimo;

import br.com.zuo.edu.biblioteca.exemplar.Exemplar;
import br.com.zuo.edu.biblioteca.usuario.Usuario;

public class EmprestimoRequest {

    private Long usuarioId;

    public EmprestimoRequest() {}

    public EmprestimoRequest(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Emprestimo toModel(Exemplar exemplar, Usuario usuario) {
        return new Emprestimo(usuario, exemplar);
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

}
