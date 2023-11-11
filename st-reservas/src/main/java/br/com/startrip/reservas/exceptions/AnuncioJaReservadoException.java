package br.com.startrip.reservas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnuncioJaReservadoException extends RuntimeException {
    public AnuncioJaReservadoException() {
        super("Este anuncio já esta reservado para o período informado.");
    }
}
