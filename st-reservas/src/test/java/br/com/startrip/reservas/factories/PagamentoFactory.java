package br.com.startrip.reservas.factories;

import br.com.startrip.reservas.domain.FormaPagamento;
import br.com.startrip.reservas.domain.Pagamento;
import br.com.startrip.reservas.domain.StatusPagamento;

import java.math.BigDecimal;

public class PagamentoFactory {
	public static Pagamento criaPagamento(StatusPagamento statusPagamento) {
		return Pagamento.builder()
				.formaEscolhida(FormaPagamento.DINHEIRO)
				.valorTotal(BigDecimal.valueOf(500))
				.status(statusPagamento)
				.build();
	}
}
