package br.com.startrip.reservas.factories;

import br.com.startrip.reservas.domain.Anuncio;
import br.com.startrip.reservas.domain.FormaPagamento;
import br.com.startrip.reservas.domain.TipoAnuncio;

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
