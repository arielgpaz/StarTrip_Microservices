package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.StatusPagamento;
import br.com.startrip.reservas.exceptions.IdReservaNaoEncontradoException;
import br.com.startrip.reservas.exceptions.StatusPagamentoException;
import br.com.startrip.reservas.repository.ReservaRepository;
import org.springframework.stereotype.Service;

@Service
public class EstornarReservaService {

    private final ReservaRepository reservaRepository;

    public EstornarReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void estornarReserva(final String idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IdReservaNaoEncontradoException(idReserva));

        if (reserva.getPagamento() != null
                && reserva.getPagamento().getStatus() == StatusPagamento.PAGO) {

            reserva.getPagamento().setStatus(StatusPagamento.ESTORNADO);
            reserva.getPagamento().setFormaEscolhida(null);

            reservaRepository.save(reserva);
        } else {
            throw new StatusPagamentoException("Não é possível realizar o estorno para esta reserva, pois ela não está no status PAGO.");
        }
    }
}
