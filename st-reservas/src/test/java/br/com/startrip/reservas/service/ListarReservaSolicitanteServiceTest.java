package br.com.startrip.reservas.service;

import br.com.startrip.reservas.factories.PeriodoFactory;
import br.com.startrip.reservas.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarReservaSolicitanteServiceTest {

    @InjectMocks
    private ListarReservaSolicitanteService service;

    @Mock
    private ReservaRepository reservaRepository;

    @Test
    void listarReservaDeSolicitante_procurarPorPeriodo() {
        var periodo = PeriodoFactory.criaPeriodo();
        when(reservaRepository.findBySolicitanteIdAndPeriodoDataHoraInicialBetweenAndPeriodoDataHoraFinalBetween(
                any(Pageable.class), anyString(), eq(periodo.getDataHoraInicial()),
                eq(periodo.getDataHoraFinal()), eq(periodo.getDataHoraInicial()), eq(periodo.getDataHoraFinal())))
                .thenReturn(Page.empty());

        service.listarReservaDeSolicitante(Pageable.unpaged(), "idSolicitante", periodo);

        verify(reservaRepository).findBySolicitanteIdAndPeriodoDataHoraInicialBetweenAndPeriodoDataHoraFinalBetween(
                any(Pageable.class), anyString(), eq(periodo.getDataHoraInicial()),
                eq(periodo.getDataHoraFinal()), eq(periodo.getDataHoraInicial()), eq(periodo.getDataHoraFinal()));
    }

    @Test
    void listarReservaDeSolicitante_naoProcurarPorPeriodo() {
        when(reservaRepository.findBySolicitanteId(any(Pageable.class), anyString())).thenReturn(Page.empty());

        service.listarReservaDeSolicitante(Pageable.unpaged(), "idSolicitante", null);

        verify(reservaRepository).findBySolicitanteId(any(Pageable.class), anyString());
    }
}
