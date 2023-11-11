package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.repository.ReservaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarReservaAnuncianteService {

    private final ReservaRepository reservaRepository;

    public ListarReservaAnuncianteService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Page<Reserva> listarReservaDeAnunciante(Pageable pageable, String cpfAnunciante) {
        return reservaRepository.findByAnuncioAnuncianteCpf(pageable, cpfAnunciante);
    }
}
