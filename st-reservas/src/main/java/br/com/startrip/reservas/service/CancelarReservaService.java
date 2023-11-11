package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.StatusPagamento;
import br.com.startrip.reservas.exceptions.IdReservaNaoEncontradoException;
import br.com.startrip.reservas.exceptions.StatusPagamentoException;
import br.com.startrip.reservas.repository.ReservaRepository;
import org.springframework.stereotype.Service;

@Service
public class CancelarReservaService {

    private final ReservaRepository reservaRepository;

    public CancelarReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void cancelarReserva(final String idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IdReservaNaoEncontradoException(idReserva));

        if (reserva.getPagamento() != null
                && reserva.getPagamento().getStatus() == StatusPagamento.PENDENTE) {

            reserva.getPagamento().setStatus(StatusPagamento.CANCELADO);

            reservaRepository.save(reserva);
        } else {
            throw new StatusPagamentoException("Não é possível realizar o cancelamento para esta reserva, pois ela não está no status PENDENTE.");
        }
    }
}
