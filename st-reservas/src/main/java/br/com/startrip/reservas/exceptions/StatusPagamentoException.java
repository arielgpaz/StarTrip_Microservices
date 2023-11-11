package br.com.startrip.reservas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusPagamentoException extends RuntimeException {
    public StatusPagamentoException(String message) {
        super(message);
    }
}
