package br.com.startrip.imoveis.factories;

import br.com.startrip.imoveis.domain.Imovel;

public class ImovelFactory {
	public static Imovel criaImovel() {
		return br.com.startrip.imoveis.domain.Imovel.builder()
				.id("3a45jd")
				.endereco(EnderecoFactory.criaEndereco())
				.tipoImovel(br.com.startrip.imoveis.domain.TipoImovel.HOTEL)
				.identificacao("Hotel dos romeiros")
				.proprietario(UsuarioFactory.criaProprietario())
				.deleted(false)
				.build();
	}
}
