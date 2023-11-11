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
class EstornarReservaServiceTest {

    @InjectMocks
    private EstornarReservaService service;

    @Mock
    private ReservaRepository reservaRepository;

    @Captor
    ArgumentCaptor<Reserva> captor;

    @Test
    void estornarReserva_deveLancarExceptionIdReservaNaoEncontrado() {
        String idReserva = "1a2b3c4d";
        when(reservaRepository.findById(idReserva)).thenReturn(Optional.empty());

        assertThrows(IdReservaNaoEncontradoException.class,
                () -> service.estornarReserva(idReserva));
    }

    @Test
    void estornarReserva_deveLancarExceptionStatusPagamentoNaoPago() {
        var reserva = ReservaFactory.criaReserva(StatusPagamento.PENDENTE);
        var idReserva = reserva.getId();
        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reserva));

        assertThrows(StatusPagamentoException.class,
                () -> service.estornarReserva(idReserva));
    }

    @Test
    void estornarReserva_comSucesso() {
        var reserva = ReservaFactory.criaReserva(StatusPagamento.PAGO);
        var idReserva = reserva.getId();
        when(reservaRepository.findById(idReserva)).thenReturn(Optional.of(reserva));

        service.estornarReserva(idReserva);

        verify(reservaRepository).save(captor.capture());
        var reservaEstornada = captor.getValue();
        assertEquals(StatusPagamento.ESTORNADO, reservaEstornada.getPagamento().getStatus());
    }
}
