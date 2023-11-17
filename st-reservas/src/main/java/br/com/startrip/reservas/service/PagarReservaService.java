package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.Pagamento;
import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.StatusPagamento;
import br.com.startrip.reservas.exceptions.IdReservaNaoEncontradoException;
import br.com.startrip.reservas.exceptions.StatusPagamentoException;
import br.com.startrip.reservas.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PagarReservaService {

    private final ReservaRepository reservaRepository;

    public void pagarReserva(final String idReserva, Pagamento pagamento) {

        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IdReservaNaoEncontradoException(idReserva));

        if (reserva.getPagamento() != null
                && reserva.getPagamento().getStatus() == StatusPagamento.PENDENTE) {

            reserva.setPagamento(pagamento);

        } else {
            throw new StatusPagamentoException(
                    "Não é possível realizar o pagamento para esta reserva, pois ela não está com status PENDENTE.");
        }

        reservaRepository.save(reserva);
    }
}
