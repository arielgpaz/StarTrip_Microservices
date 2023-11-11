package br.com.startrip.imoveis.factories;

import br.com.startrip.imoveis.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {
	public static Usuario criaProprietario() {
		return Usuario.builder()
				.id("44no8s9")
				.nome("Jesus")
				.cpf("12345678901")
				.dataNascimento(LocalDate.of(0, 12, 25))
				.email("jesus@ceu.com.br")
				.senha("123456")
				.endereco(EnderecoFactory.criaEndereco())
				.build();
	}
}
