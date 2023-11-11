package br.com.startrip.anuncios.factories;

import br.com.startrip.anuncios.domain.Usuario;

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
}
