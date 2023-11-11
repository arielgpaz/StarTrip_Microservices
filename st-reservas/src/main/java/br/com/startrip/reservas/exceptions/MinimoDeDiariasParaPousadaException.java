package br.com.startrip.reservas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimoDeDiariasParaPousadaException extends RuntimeException {
    public MinimoDeDiariasParaPousadaException() {
        super("Não é possível realizar uma reserva com menos de 5 diárias para imóveis do tipo Pousada");
    }
}
