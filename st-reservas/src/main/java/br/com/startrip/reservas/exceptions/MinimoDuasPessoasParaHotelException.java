package br.com.startrip.reservas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimoDuasPessoasParaHotelException extends RuntimeException {
    public MinimoDuasPessoasParaHotelException() {
        super("Não é possível realizar uma reserva com menos de 2 pessoas para imóveis do tipo Hotel");
    }
}
