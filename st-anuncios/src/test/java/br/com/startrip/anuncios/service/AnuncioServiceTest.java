package br.com.startrip.anuncios.service;

import br.com.startrip.anuncios.domain.Anuncio;
import br.com.startrip.anuncios.exceptions.CpfUsuarioInexistenteException;
import br.com.startrip.anuncios.exceptions.IdAnuncioNaoEncontradoException;
import br.com.startrip.anuncios.exceptions.IdImovelInexistenteException;
import br.com.startrip.anuncios.exceptions.ImovelJaAnunciadoException;
import br.com.startrip.anuncios.factories.AnuncioFactory;
import br.com.startrip.anuncios.factories.CadastrarAnuncioRequestFactory;
import br.com.startrip.anuncios.factories.ImovelFactory;
import br.com.startrip.anuncios.factories.UsuarioFactory;
import br.com.startrip.anuncios.feign.ApiStImoveis;
import br.com.startrip.anuncios.feign.ApiStUsuarios;
import br.com.startrip.anuncios.repository.AnuncioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnuncioServiceTest {

    @InjectMocks
    private AnuncioService service;

    @Mock
    private AnuncioRepository repository;

    @Mock
    private ApiStImoveis stImovel;

    @Mock
    private ApiStUsuarios stUsuarios;

    @Captor
    private ArgumentCaptor<Anuncio> captor;

    @Test
    void anunciarImovel_deveRetornarExceptionIdImovelNaoEncontrado() {
        var request = CadastrarAnuncioRequestFactory.criarRequest();
        when(stImovel.buscarImovelPorId(request.getIdImovel())).thenReturn(Optional.empty());

        assertThrows(IdImovelInexistenteException.class,
                () -> service.anunciarImovel(request));
    }

    @Test
    void anunciarImovel_deveRetornarExceptionImovelAnunciado() {
        var request = CadastrarAnuncioRequestFactory.criarRequest();
        when(stImovel.buscarImovelPorId(request.getIdImovel())).thenReturn(Optional.of(ImovelFactory.criaImovel()));
        when(repository.existsByImovelIdAndDeletedIs(request.getIdImovel(), false)).thenReturn(true);

        assertThrows(ImovelJaAnunciadoException.class,
                () -> service.anunciarImovel(request));
    }

    @Test
    void anunciarImovel_deveRetornarExceptionCpfUsuarioInexistente() {
        var request = CadastrarAnuncioRequestFactory.criarRequest();
        when(stImovel.buscarImovelPorId(request.getIdImovel())).thenReturn(Optional.of(ImovelFactory.criaImovel()));
        when(repository.existsByImovelIdAndDeletedIs(request.getIdImovel(), false)).thenReturn(false);
        when(stUsuarios.buscarUsuarioPorCpf(request.getCpfAnunciante())).thenReturn(Optional.empty());

        assertThrows(CpfUsuarioInexistenteException.class,
                () -> service.anunciarImovel(request));
    }

    @Test
    void anunciarImovel_comSucesso() {
        var request = CadastrarAnuncioRequestFactory.criarRequest();
        var imovel = ImovelFactory.criaImovel();
        var anunciante = UsuarioFactory.criaAnunciante();
        when(stImovel.buscarImovelPorId(request.getIdImovel())).thenReturn(Optional.of(imovel));
        when(repository.existsByImovelIdAndDeletedIs(request.getIdImovel(), false)).thenReturn(false);
        when(stUsuarios.buscarUsuarioPorCpf(request.getCpfAnunciante())).thenReturn(Optional.of(anunciante));
        when(repository.save(any(Anuncio.class))).thenReturn(new Anuncio());

        service.anunciarImovel(request);

        verify(repository).save(captor.capture());
        var anuncioFeito = captor.getValue();
        assertEquals(request.getTipoAnuncio(), anuncioFeito.getTipoAnuncio());
        assertEquals(request.getValorDiaria(), anuncioFeito.getValorDiaria());
        assertEquals(request.getFormasAceitas(), anuncioFeito.getFormasAceitas());
        assertEquals(request.getDescricao(), anuncioFeito.getDescricao());
        assertEquals(imovel, anuncioFeito.getImovel());
        assertEquals(anunciante, anuncioFeito.getAnunciante());
    }

    @Test
    void listarAnuncios_deveChamarRepositoryComSucesso() {
        when(repository.findByDeletedIs(eq(false), any(Pageable.class))).thenReturn(Page.empty());

        var anuncios = service.listarAnuncios(Pageable.unpaged());

        assertEquals(0, anuncios.getTotalElements());
    }

    @Test
    void listarAnunciosDeAnunciante_deveRetornarExceptionCpfUsuarioInexistente() {
        var cpf = "12345678901";
        var pageable = Pageable.unpaged();
        when(stUsuarios.buscarUsuarioPorCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(CpfUsuarioInexistenteException.class,
                () -> service.listarAnunciosDeAnunciante(pageable, cpf));
    }

    @Test
    void listarAnunciosDeAnunciante_deveChamarRepositoryComSucesso() {
        var anunciante = UsuarioFactory.criaAnunciante();
        var cpf = "12345678901";
        when(stUsuarios.buscarUsuarioPorCpf(cpf)).thenReturn(Optional.of(anunciante));
        when(repository.findByAnuncianteAndDeletedIs(any(Pageable.class), eq(anunciante), eq(false)))
                .thenReturn(Page.empty());

        var anuncios = service.listarAnunciosDeAnunciante(Pageable.unpaged(), cpf);

        assertEquals(0, anuncios.getTotalElements());
    }

    @Test
    void excluirAnuncio_deveRetornarExceptionIdAnuncioNaoEncontrado() {
        var idAnuncio = "1a2b3c4d";
        when(repository.findByIdAndDeletedIs(idAnuncio, false)).thenReturn(Optional.empty());

        assertThrows(IdAnuncioNaoEncontradoException.class,
                () -> service.excluirAnuncio(idAnuncio));
    }

    @Test
    void excluirAnuncio_comSucesso() {
        Anuncio anuncio = AnuncioFactory.criaAnuncio();
        var idAnuncio = anuncio.getId();
        when(repository.findByIdAndDeletedIs(idAnuncio, false)).thenReturn(Optional.of(anuncio));
        when(repository.save(anuncio)).thenReturn(new Anuncio());

        service.excluirAnuncio(idAnuncio);

        verify(repository).save(captor.capture());
        var anuncioExcluido = captor.getValue();
        assertTrue(anuncioExcluido.isDeleted());
    }

    @Test
    void verificarSeExisteAnuncioParaImovel_deveChamarRepositoryComSucesso() {
        var imovel = ImovelFactory.criaImovel();
        when(repository.existsByImovelIdAndDeletedIs(imovel.getId(), false)).thenReturn(true);

        var existe = service.verificarSeExisteAnuncioParaImovel(imovel.getId());

        assertTrue(existe);
    }

    @Test
    void buscarAnuncioPorId_deveChamarRepositoryComSucesso() {
        var anuncio = AnuncioFactory.criaAnuncio();
        var id = anuncio.getId();
        when(repository.findByIdAndDeletedIs(id, false)).thenReturn(Optional.of(anuncio));

        var anuncioEncontrado = service.buscarAnuncioPorId(id);

        assertNotNull(anuncioEncontrado);
    }

    @Test
    void buscarAnuncioPorId_deveRetornarExceptionIdImovelNaoEncontrado() {
        when(repository.findByIdAndDeletedIs(anyString(), eq(false))).thenReturn(Optional.empty());

        assertThrows(IdAnuncioNaoEncontradoException.class,
                () -> service.buscarAnuncioPorId("id"));
    }
}
