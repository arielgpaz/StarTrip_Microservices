package br.com.startrip.reservas.factories;

import br.com.startrip.reservas.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {
	public static Usuario criaAnunciante() {
		return Usuario.builder()
				.id("44no8s9")
				.nome("Jesus")
				.cpf("12345678901")
				.dataNascimento(LocalDate.of(0, 12, 25))
				.email("jesus@ceu.com.br")
				.endereco(EnderecoFactory.criaEndereco())
				.build();
	}

	public static Usuario criaSolicitante() {
		return Usuario.builder()
				.id("8fvn4df9")
				.nome("João")
				.cpf("12345678902")
				.dataNascimento(LocalDate.of(2, 1, 18))
				.email("joao@ceu.com.br")
				.endereco(EnderecoFactory.criaEndereco())
				.build();
	}
}
