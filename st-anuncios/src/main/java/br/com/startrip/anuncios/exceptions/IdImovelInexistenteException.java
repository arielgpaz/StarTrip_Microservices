package br.com.startrip.anuncios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdImovelInexistenteException extends RuntimeException {
	public IdImovelInexistenteException(String id) {
		super(String.format("Nenhum(a) Imovel com Id com o valor '%s' foi encontrado.", id));
	}
}
