package br.com.zuo.edu.biblioteca.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadronizado> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        Integer codigoHttp = badRequestStatus.value();
        String mensagemHttp = badRequestStatus.getReasonPhrase();
        String mensagemGeral = "Houve um erro de validação.";
        List<String> mensagens = new ArrayList<>();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            mensagens.add(
                "O campo " + fieldError.getField() + " " + fieldError.getDefaultMessage() + "."
            );
        }

        ErroPadronizado erroPadronizado = new ErroPadronizado();
    }

}
