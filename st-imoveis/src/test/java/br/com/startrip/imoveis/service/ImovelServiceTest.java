package br.com.startrip.imoveis.service;

import br.com.startrip.imoveis.domain.Imovel;
import br.com.startrip.imoveis.exceptions.IdImovelInexistenteException;
import br.com.startrip.imoveis.exceptions.ImovelPossuiAnuncioException;
import br.com.startrip.imoveis.exceptions.ProprietarioNaoEncontradoPeloCpfException;
import br.com.startrip.imoveis.factories.CadastrarImovelRequestFactory;
import br.com.startrip.imoveis.factories.ImovelFactory;
import br.com.startrip.imoveis.factories.UsuarioFactory;
import br.com.startrip.imoveis.feign.ApiStAnuncios;
import br.com.startrip.imoveis.feign.ApiStUsuarios;
import br.com.startrip.imoveis.repository.ImovelRepository;
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
class ImovelServiceTest {

    @InjectMocks
    private ImovelService service;

    @Mock
    private ImovelRepository repository;

    @Mock
    private ApiStUsuarios stUsuarios;

    @Mock
    private ApiStAnuncios stAnuncios;

    @Captor
    private ArgumentCaptor<Imovel> captor;

    @Test
    void cadastrarImovel_naoEncontrarProprietarioCadastrado_deveLancarException() {
        var request = CadastrarImovelRequestFactory.criarRequest();
        when(stUsuarios.buscarUsuarioPorCpf(request.getCpfProprietario())).thenReturn(Optional.empty());

        assertThrows(ProprietarioNaoEncontradoPeloCpfException.class,
                () -> service.cadastrarImovel(request));
    }

    @Test
    void cadastrarImovel_comSucesso() {
        var request = CadastrarImovelRequestFactory.criarRequest();
        var proprietario = UsuarioFactory.criaProprietario();
        when(stUsuarios.buscarUsuarioPorCpf(request.getCpfProprietario()))
                .thenReturn(Optional.of(proprietario));
        when(repository.save(any(Imovel.class))).thenReturn(Imovel.builder().build());

        service.cadastrarImovel(request);

        verify(repository).save(captor.capture());
        var imovelCapturado = captor.getValue();
        assertEquals(request.getCpfProprietario(), imovelCapturado.getProprietario().getCpf());
        assertEquals(request.getIdentificacao(), imovelCapturado.getIdentificacao());
        assertEquals(request.getEndereco(), imovelCapturado.getEndereco());
        assertEquals(request.getCaracteristicas(), imovelCapturado.getCaracteristicas());
        assertEquals(request.getTipoImovel(), imovelCapturado.getTipoImovel());
    }

    @Test
    void listarImoveis_deveChamarRepositoryComSucesso() {
        when(repository.findByDeletedIs(eq(false), any(Pageable.class))).thenReturn(Page.empty());

        var imoveis = service.listarImoveis(Pageable.unpaged());

        assertEquals(0, imoveis.getTotalElements());
    }

    @Test
    void listarImoveisDeProprietario_deveChamarRepositoryComSucesso() {
        var cpf = "12345678901";
        when(repository.findByProprietarioCpfEqualsAndDeletedIs(any(Pageable.class), eq(cpf), eq(false)))
                .thenReturn(Page.empty());

        var imoveis = service.listarImoveisDeProprietario(Pageable.unpaged(), cpf);

        assertEquals(0, imoveis.getTotalElements());
    }

    @Test
    void buscarImovelPorId_deveChamarRepositoryComSucesso() {
        var imovel = ImovelFactory.criaImovel();
        var id = imovel.getId();
        when(repository.findByIdAndDeletedIs(id, false)).thenReturn(Optional.of(imovel));

        var imovelEncontrado = service.buscarImovelPorId(id);

        assertNotNull(imovelEncontrado);
    }

    @Test
    void buscarImovelPorId_deveRetornarExceptionIdImovelNaoEncontrado() {
        when(repository.findByIdAndDeletedIs(anyString(), eq(false))).thenReturn(Optional.empty());

        assertThrows(IdImovelInexistenteException.class,
                () -> service.buscarImovelPorId("id"));
    }

    @Test
    void excluirImovel_deveRetornarExceptionImovelPossuiAnuncio() {
        var imovel = ImovelFactory.criaImovel();
        var id = imovel.getId();
        when(repository.findByIdAndDeletedIs(id, false)).thenReturn(Optional.of(imovel));
        when(stAnuncios.existeAnuncioParaImovel(id)).thenReturn(true);

        assertThrows(ImovelPossuiAnuncioException.class,
                () -> service.excluirImovel(id));
    }

    @Test
    void excluirImovel_comSucesso() {
        var imovel = ImovelFactory.criaImovel();
        var id = imovel.getId();
        when(repository.findByIdAndDeletedIs(id, false)).thenReturn(Optional.of(imovel));
        when(stAnuncios.existeAnuncioParaImovel(id)).thenReturn(false);
        when(repository.save(imovel)).thenReturn(imovel);

        service.excluirImovel(id);

        verify(repository).save(captor.capture());
        var imovelExcluido = captor.getValue();
        assertTrue(imovelExcluido.isDeleted());
    }
}
