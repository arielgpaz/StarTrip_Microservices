package br.com.startrip.reservas.repository;

import br.com.startrip.reservas.domain.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {

    List<Reserva> findByAnuncioId(String id);

    Page<Reserva> findByAnuncioAnuncianteCpf(Pageable pageable, String cpfAnunciante);

    Page<Reserva> findBySolicitanteIdAndPeriodoDataHoraInicialBetweenAndPeriodoDataHoraFinalBetween(Pageable pageable, String idSolicitante, LocalDateTime dataHoraInicial1, LocalDateTime dataHoraFinal1, LocalDateTime dataHoraInicial2, LocalDateTime dataHoraFinal2);

    Page<Reserva> findBySolicitanteId(Pageable pageable, String idSolicitante);
}
