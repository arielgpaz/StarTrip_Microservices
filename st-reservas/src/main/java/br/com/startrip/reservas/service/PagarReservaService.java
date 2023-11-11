package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.FormaPagamento;
import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.StatusPagamento;
import br.com.startrip.reservas.exceptions.FormaPagamentoException;
import br.com.startrip.reservas.exceptions.IdReservaNaoEncontradoException;
import br.com.startrip.reservas.exceptions.StatusPagamentoException;
import br.com.startrip.reservas.repository.ReservaRepository;
import org.springframework.stereotype.Service;

@Service
public class PagarReservaService {

    private final ReservaRepository reservaRepository;

    public PagarReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void pagarReserva(final String idReserva, FormaPagamento formaPagamento) {

        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IdReservaNaoEncontradoException(idReserva));

        if (reserva.getAnuncio() != null
                && reserva.getAnuncio().getFormasAceitas() != null
                && !reserva.getAnuncio().getFormasAceitas().contains(formaPagamento)) {

            throw new FormaPagamentoException(formaPagamento, reserva.getAnuncio()
                    .getFormasAceitas());
        }

        if (reserva.getPagamento() != null
                && reserva.getPagamento().getStatus() == StatusPagamento.PENDENTE) {

            reserva.getPagamento()
                    .setStatus(StatusPagamento.PAGO);

            reserva.getPagamento()
                    .setFormaEscolhida(formaPagamento);

        } else {
            throw new StatusPagamentoException("Não é possível realizar o pagamento para esta reserva, pois ela não está no status PENDENTE.");
        }

        reservaRepository.save(reserva);
    }
}
