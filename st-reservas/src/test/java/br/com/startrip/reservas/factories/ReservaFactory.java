package br.com.startrip.reservas.factories;

import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.StatusPagamento;

import java.time.LocalDateTime;

public class ReservaFactory {
	public static Reserva criaReserva(StatusPagamento statusPagamento) {
		return Reserva.builder()
				.id("3241on")
				.solicitante(UsuarioFactory.criaSolicitante())
				.anuncio(AnuncioFactory.criaAnuncio())
				.quantidadePessoas(3)
				.periodo(PeriodoFactory.criaPeriodo())
				.pagamento(PagamentoFactory.criaPagamento(statusPagamento))
				.dataHoraReserva(LocalDateTime.now())
				.build();
	}
}
