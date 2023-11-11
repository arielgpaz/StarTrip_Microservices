package br.com.startrip.reservas.service;

import br.com.startrip.reservas.repository.ReservaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarReservaAnuncianteServiceTest {

    @InjectMocks
    private ListarReservaAnuncianteService service;

    @Mock
    private ReservaRepository reservaRepository;

    @Test
    void listarReservaDeAnunciante_comSucesso() {
        when(reservaRepository.findByAnuncioAnuncianteCpf(any(Pageable.class), anyString()))
                .thenReturn(Page.empty());

        var reservas = service.listarReservaDeAnunciante(Pageable.unpaged(), "12345678901");

        Assertions.assertEquals(0, reservas.getTotalElements());
    }
}
