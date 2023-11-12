package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.StatusPagamento;
import br.com.startrip.reservas.exceptions.IdReservaNaoEncontradoException;
import br.com.startrip.reservas.exceptions.StatusPagamentoException;
import br.com.startrip.reservas.factories.ReservaFactory;
import br.com.startrip.reservas.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CancelarReservaServiceTest {

    @InjectMocks
    private CancelarReservaService service;

    @Mock
    private ReservaRepository reservaRepository;

    @Captor
    ArgumentCaptor<Reserva> captor;

//    @Test
//    void cancelarReserva_deveLancarExceptionIdReservaNaoEncontrado() {
//        String idReserva = "1a2b3c4d";
//        when(reservaRepository.findById(idReserva)).thenReturn(Optional.empty());
//
//        assertThrows(IdReservaNaoEncontradoException.class,
//                () -> service.cancelarReserva(idReserva));
//    }
//
//    @Test
//    void cancelarReserva_deveLancarExceptionStatusPagamentoNaoPendente() {
//        var reserva = ReservaFactory.criaReserva(StatusPagamento.CANCELADO);
//        var idReserva = reserva.getId();
//        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reserva));
//
//        assertThrows(StatusPagamentoException.class,
//                () -> service.cancelarReserva(idReserva));
//    }
//
//    @Test
//    void cancelarReserva_comSucesso() {
//        var reserva = ReservaFactory.criaReserva(StatusPagamento.PENDENTE);
//        var idReserva = reserva.getId();
//        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reserva));
//
//        service.cancelarReserva(idReserva);
//
//        verify(reservaRepository).save(captor.capture());
//        var reservaCancelada = captor.getValue();
//        assertEquals(StatusPagamento.CANCELADO, reservaCancelada.getPagamento().getStatus());
//    }
}
