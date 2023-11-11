package br.com.startrip.reservas.service;

import br.com.startrip.reservas.controller.request.CadastrarReservaRequest;
import br.com.startrip.reservas.domain.*;
import br.com.startrip.reservas.exceptions.*;
import br.com.startrip.reservas.factories.AnuncioFactory;
import br.com.startrip.reservas.factories.CadastrarReservaRequestFactory;
import br.com.startrip.reservas.factories.ReservaFactory;
import br.com.startrip.reservas.factories.UsuarioFactory;
import br.com.startrip.reservas.feign.ApiStAnuncios;
import br.com.startrip.reservas.feign.ApiStUsuarios;
import br.com.startrip.reservas.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RealizarReservaServiceTest {

    @InjectMocks
    private RealizarReservaService service;

    @Mock
    private ApiStUsuarios apiStUsuarios;

    @Mock
    private ApiStAnuncios apiStAnuncios;

    @Mock
    private ReservaRepository reservaRepository;

    private CadastrarReservaRequest request;

    @BeforeEach
    public void setUp() {
        request = CadastrarReservaRequestFactory.criarRequest();
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionCpfUsuarioInexistente() {
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante())).thenReturn(Optional.empty());

        assertThrows(CpfUsuarioInexistenteException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionIdAnuncioNaoEncontrado() {
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(UsuarioFactory.criaSolicitante()));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio())).thenReturn(Optional.empty());

        assertThrows(IdAnuncioNaoEncontradoException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionSolicitanteMesmoAnunciante() {
        Usuario solicitante = UsuarioFactory.criaSolicitante();
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        anuncio.setAnunciante(solicitante);
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(solicitante));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio()))
                .thenReturn(Optional.of(anuncio));

        assertThrows(SolicitanteMesmoAnuncianteException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionAnuncioJaReservado() {
        Usuario solicitante = UsuarioFactory.criaSolicitante();
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        Reserva reserva = ReservaFactory.criaReserva(StatusPagamento.PAGO);
        reserva.setPeriodo(request.getPeriodo());
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(solicitante));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio()))
                .thenReturn(Optional.of(anuncio));
        when(reservaRepository.findByAnuncioId(anuncio.getId())).thenReturn(List.of(reserva));

        assertThrows(AnuncioJaReservadoException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionDataFinalMenor() {
        Usuario solicitante = UsuarioFactory.criaSolicitante();
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(solicitante));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio()))
                .thenReturn(Optional.of(anuncio));
        when(reservaRepository.findByAnuncioId(anuncio.getId())).thenReturn(Collections.emptyList());
        request.getPeriodo().setDataHoraInicial(LocalDateTime.of(2022, 7, 20, 12, 0));

        assertThrows(DataFinalMenorException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionNumeroMinimoDiarias() {
        Usuario solicitante = UsuarioFactory.criaSolicitante();
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(solicitante));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio()))
                .thenReturn(Optional.of(anuncio));
        when(reservaRepository.findByAnuncioId(anuncio.getId())).thenReturn(Collections.emptyList());
        request.getPeriodo().setDataHoraInicial(LocalDateTime.of(2022, 7, 15, 12, 0));

        assertThrows(NumeroMinimoDiariasException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionMinimoDuasPessoasParaHotel() {
        Usuario solicitante = UsuarioFactory.criaSolicitante();
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(solicitante));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio()))
                .thenReturn(Optional.of(anuncio));
        when(reservaRepository.findByAnuncioId(anuncio.getId())).thenReturn(Collections.emptyList());
        request.setQuantidadePessoas(1);

        assertThrows(MinimoDuasPessoasParaHotelException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_deveLancarExceptionMinimoDeDiariasParaPousada() {
        Usuario solicitante = UsuarioFactory.criaSolicitante();
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        anuncio.getImovel().setTipoImovel(TipoImovel.POUSADA);
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(solicitante));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio()))
                .thenReturn(Optional.of(anuncio));
        when(reservaRepository.findByAnuncioId(anuncio.getId())).thenReturn(Collections.emptyList());
        request.getPeriodo().setDataHoraInicial(LocalDateTime.of(2022, 7, 14, 12, 0));

        assertThrows(MinimoDeDiariasParaPousadaException.class,
                () -> service.realizarUmaReserva(request));
    }

    @Test
    void realizarUmaReserva_comSucesso() {
        Usuario solicitante = UsuarioFactory.criaSolicitante();
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        when(apiStUsuarios.buscarUsuarioPorCpf(request.getCpfSolicitante()))
                .thenReturn(Optional.of(solicitante));
        when(apiStAnuncios.buscarAnuncioPorId(request.getIdAnuncio()))
                .thenReturn(Optional.of(anuncio));
        when(reservaRepository.findByAnuncioId(anuncio.getId())).thenReturn(Collections.emptyList());
        when(reservaRepository.save(any(Reserva.class))).thenReturn(ReservaFactory.criaReserva(StatusPagamento.PENDENTE));

        var informacaoReservaResponse = service.realizarUmaReserva(request);

        assertNotNull(informacaoReservaResponse);
    }

}
