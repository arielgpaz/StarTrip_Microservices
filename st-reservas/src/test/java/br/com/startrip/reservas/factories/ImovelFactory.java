package br.com.startrip.reservas.factories;

import br.com.startrip.reservas.domain.Imovel;
import br.com.startrip.reservas.domain.TipoImovel;

public class ImovelFactory {
	public static Imovel criaImovel() {
		return Imovel.builder()
				.id("3a45jd")
				.endereco(EnderecoFactory.criaEndereco())
				.tipoImovel(TipoImovel.HOTEL)
				.identificacao("Hotel dos romeiros")
				.proprietario(UsuarioFactory.criaAnunciante())
				.build();
	}
}
