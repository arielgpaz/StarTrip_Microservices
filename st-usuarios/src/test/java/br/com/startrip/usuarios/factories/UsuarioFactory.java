package br.com.startrip.usuarios.factories;

import br.com.startrip.usuarios.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {
	public static Usuario criaUsuario() {
		return Usuario.builder()
				.id("14L")
				.nome("Jesus")
				.cpf("12345678901")
				.dataNascimento(LocalDate.of(0, 12, 25))
				.email("jesus@ceu.com.br")
				.senha("123456")
				.endereco(EnderecoFactory.criaEndereco())
				.build();
	}
}
