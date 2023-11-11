package br.com.startrip.usuarios.factories;


import br.com.startrip.usuarios.domain.Endereco;

public class EnderecoFactory {
	public static Endereco criaEndereco() {
		return Endereco.builder()
				.cep("11222-333")
				.logradouro("Rua dos Anjos")
				.numero("2")
				.bairro("Centro")
				.cidade("Louvores")
				.complemento("Hotel com jardim na entrada")
				.estado("Primeiro Estado")
				.build();
	}
}
