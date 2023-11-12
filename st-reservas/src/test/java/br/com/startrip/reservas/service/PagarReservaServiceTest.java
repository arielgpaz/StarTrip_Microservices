package br.com.startrip.reservas.service;

import br.com.startrip.reservas.domain.FormaPagamento;
import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.StatusPagamento;
import br.com.startrip.reservas.exceptions.FormaPagamentoException;
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
class PagarReservaServiceTest {

    @InjectMocks
    private PagarReservaService service;

    @Mock
    private ReservaRepository reservaRepository;

    @Captor
    ArgumentCaptor<Reserva> captor;

//    @Test
//    void pagarReserva_deveLancarExceptionIdReservaNaoEncontrado() {
//        when(reservaRepository.findById("idReserva")).thenReturn(Optional.empty());
//
//        assertThrows(IdReservaNaoEncontradoException.class,
//                () -> service.pagarReserva("idReserva", FormaPagamento.PIX));
//    }
//
//    @Test
//    void pagarReserva_deveLancarExceptionFormaPagamentoNaoAceita() {
//        var reserva = ReservaFactory.criaReserva(StatusPagamento.PENDENTE);
//        var idReserva = reserva.getId();
//        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reserva));
//
//        assertThrows(FormaPagamentoException.class,
//                () -> service.pagarReserva(idReserva, FormaPagamento.PIX));
//    }
//
//    @Test
//    void pagarReserva_deveLancarExceptionStatusPagamentoNaoPendente() {
//        var reserva = ReservaFactory.criaReserva(StatusPagamento.PAGO);
//        var idReserva = reserva.getId();
//        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reserva));
//
//        assertThrows(StatusPagamentoException.class,
//                () -> service.pagarReserva(idReserva, FormaPagamento.CARTAO_CREDITO));
//    }
//
//    @Test
//    void pagarReserva_comSucesso() {
//        var reserva = ReservaFactory.criaReserva(StatusPagamento.PENDENTE);
//        var idReserva = reserva.getId();
//        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reserva));
//
//        service.pagarReserva(idReserva, FormaPagamento.CARTAO_CREDITO);
//
//        verify(reservaRepository).save(captor.capture());
//        var reservaPaga = captor.getValue();
//        assertEquals(StatusPagamento.PAGO, reservaPaga.getPagamento().getStatus());
//    }
}
