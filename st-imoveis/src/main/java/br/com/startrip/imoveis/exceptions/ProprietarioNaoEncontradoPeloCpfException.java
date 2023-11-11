package br.com.startrip.imoveis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProprietarioNaoEncontradoPeloCpfException extends RuntimeException {
	public ProprietarioNaoEncontradoPeloCpfException(String cpfProprietario) {
		super(String.format("Nenhum(a) Usuario com Id com o valor '%s' foi encontrado.", cpfProprietario));
	}
}
