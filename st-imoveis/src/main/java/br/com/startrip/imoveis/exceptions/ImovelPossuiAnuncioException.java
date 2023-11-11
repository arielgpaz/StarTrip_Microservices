package br.com.startrip.imoveis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImovelPossuiAnuncioException extends RuntimeException {
	public ImovelPossuiAnuncioException() {
		super("Não é possível excluir um imóvel que possua um anúncio.");
	}
}
