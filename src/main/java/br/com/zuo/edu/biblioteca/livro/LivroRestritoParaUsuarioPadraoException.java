package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LivroRestritoParaUsuarioPadraoException extends RuntimeException{
    public LivroRestritoParaUsuarioPadraoException(String message) {
        super(message);
    }
}
