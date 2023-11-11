package br.com.startrip.anuncios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfUsuarioInexistenteException extends RuntimeException {
	public CpfUsuarioInexistenteException(String id) {
		super(String.format("Nenhum(a) Usuario com Id com o valor '%s' foi encontrado.", id));
	}
}
