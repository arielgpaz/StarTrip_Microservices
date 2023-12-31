package br.com.startrip.reservas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdReservaNaoEncontradoException extends RuntimeException {
    public IdReservaNaoEncontradoException(String idReserva) {
        super(String.format("Nenhuma Reserva com Id com o valor '%s' foi encontrada.", idReserva));
    }
}
