package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class QuantidadeMaximaDeEmprestimoParaUsuarioPadraoException extends RuntimeException {
    public QuantidadeMaximaDeEmprestimoParaUsuarioPadraoException(String message) {
        super(message);
    }
}
