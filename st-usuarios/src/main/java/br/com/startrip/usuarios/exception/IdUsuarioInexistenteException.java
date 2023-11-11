package br.com.startrip.usuarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdUsuarioInexistenteException extends RuntimeException {
	public IdUsuarioInexistenteException(String id) {
		super(String.format("Nenhum(a) Usuario com Id com o valor '%s' foi encontrado.", id));
	}
}
