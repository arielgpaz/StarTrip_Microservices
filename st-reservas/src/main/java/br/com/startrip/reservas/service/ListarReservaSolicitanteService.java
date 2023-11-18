package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.Periodo;
import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListarReservaSolicitanteService {

    private final ReservaRepository reservaRepository;

    public Page<Reserva> listarReservaDeSolicitante(Pageable pageable, String idSolicitante, Periodo periodo) {

        Page<Reserva> reservasDoSolicitante;

        if (periodo != null
                && periodo.getDataHoraInicial() != null
                && periodo.getDataHoraFinal() != null) {

            reservasDoSolicitante = reservaRepository
                    .findBySolicitanteIdAndPeriodoDataHoraInicialBetweenAndPeriodoDataHoraFinalBetween(
                            pageable,
                            idSolicitante,
                            periodo.getDataHoraInicial(), periodo.getDataHoraFinal(),
                            periodo.getDataHoraInicial(), periodo.getDataHoraFinal());

        } else {
            reservasDoSolicitante = reservaRepository.findBySolicitanteId(pageable, idSolicitante);
        }
        return reservasDoSolicitante;
    }
}
