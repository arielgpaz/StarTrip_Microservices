package br.com.startrip.anuncios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdAnuncioNaoEncontradoException extends RuntimeException {
	public IdAnuncioNaoEncontradoException(String idAnuncio) {
		super(String.format("Nenhum(a) Anuncio com Id com o valor '%s' foi encontrado.", idAnuncio));
	}
}
