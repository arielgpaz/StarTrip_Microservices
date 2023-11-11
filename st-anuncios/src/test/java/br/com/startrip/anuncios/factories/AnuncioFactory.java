package br.com.startrip.anuncios.factories;

import br.com.startrip.anuncios.domain.Anuncio;
import br.com.startrip.anuncios.domain.FormaPagamento;
import br.com.startrip.anuncios.domain.TipoAnuncio;

import java.math.BigDecimal;
import java.util.Arrays;

public class AnuncioFactory {
	public static Anuncio criaAnuncio() {
		return Anuncio.builder()
				.id("3on43o4")
				.descricao("Quarto com sacada de frente para a praia")
				.tipoAnuncio(TipoAnuncio.QUARTO)
				.anunciante(UsuarioFactory.criaAnunciante())
				.imovel(ImovelFactory.criaImovel())
				.valorDiaria(BigDecimal.valueOf(100))
				.formasAceitas(Arrays.asList(FormaPagamento.DINHEIRO, FormaPagamento.CARTAO_CREDITO, FormaPagamento.CARTAO_DEBITO))
				.build();
	}
}
