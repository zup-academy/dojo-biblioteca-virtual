package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PrazoSemDefinicaoParaUsuarioPadraoException extends RuntimeException{
    public PrazoSemDefinicaoParaUsuarioPadraoException(String message){
        super(message);
    }
}
