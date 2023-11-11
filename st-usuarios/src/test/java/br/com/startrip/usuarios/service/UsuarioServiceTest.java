package br.com.startrip.usuarios.service;

import br.com.startrip.usuarios.controller.request.AtualizarUsuarioRequest;
import br.com.startrip.usuarios.domain.Usuario;
import br.com.startrip.usuarios.exception.CpfJaCadastradoParaOutroUsuarioException;
import br.com.startrip.usuarios.exception.CpfUsuarioInexistenteException;
import br.com.startrip.usuarios.exception.EmailJaCadastradoParaOutroUsuarioException;
import br.com.startrip.usuarios.exception.IdUsuarioInexistenteException;
import br.com.startrip.usuarios.factories.AtualizarUsuarioRequestFactory;
import br.com.startrip.usuarios.factories.UsuarioFactory;
import br.com.startrip.usuarios.repository.UsuarioRepository;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Captor
    ArgumentCaptor<Usuario> captor;

    @Test
    void cadastrar_deveRetornarExceptionEmailJaCadastrado() {
        var usuario = new Usuario();
        when(repository.existsByEmail(usuario.getEmail())).thenReturn(true);

        assertThrows(EmailJaCadastradoParaOutroUsuarioException.class,
                () -> service.cadastrar(usuario));
    }

    @Test
    void cadastrar_deveRetornarExceptionCpfJaCadastrado() {
        var usuario = new Usuario();
        when(repository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(repository.existsByCpf(usuario.getCpf())).thenReturn(true);

        assertThrows(CpfJaCadastradoParaOutroUsuarioException.class,
                () -> service.cadastrar(usuario));
    }

    @Test
    void cadastrar_comSucesso() {
        var usuario = new Usuario();
        when(repository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(repository.existsByCpf(usuario.getCpf())).thenReturn(false);
        when(repository.save(usuario)).thenReturn(usuario);

        service.cadastrar(usuario);

        verify(repository).save(captor.capture());
        var usuarioCapturado = captor.getValue();
        assertEquals(usuario, usuarioCapturado);
    }

    @Test
    void listarUsuarios_deveChamarRepositoryComSucesso() {
        when(repository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        var usuarios = service.listarUsuarios(Pageable.unpaged());

        assertEquals(0, usuarios.getTotalElements());
    }

    @Test
    void buscarUsuarioPorId_deveChamarRepositoryRetornarSucesso() {
        when(repository.findById("id")).thenReturn(Optional.of(UsuarioFactory.criaUsuario()));

        var usuario = service.buscarUsuarioPorId("id");

        assertNotNull(usuario);
    }

    @Test
    void buscarUsuarioPorId_deveChamarRepositoryRetornarVazio() {
        when(repository.findById("id")).thenReturn(Optional.empty());

        assertThrows(IdUsuarioInexistenteException.class, () ->
                service.buscarUsuarioPorId("id"));
    }

    @Test
    void buscarUsuarioPorCpf_deveChamarRepositoryRetornarSucesso() {
        when(repository.findByCpf("12345678901")).thenReturn(Optional.of(UsuarioFactory.criaUsuario()));

        var usuario = service.buscarUsuarioPorCpf("12345678901");

        assertNotNull(usuario);
    }

    @Test
    void buscarUsuarioPorCpf_deveChamarRepositoryRetornarVazio() {
        when(repository.findByCpf("12345678901")).thenReturn(Optional.empty());

        assertThrows(CpfUsuarioInexistenteException.class, () ->
                service.buscarUsuarioPorCpf("12345678901"));
    }

    @Test
    void alterarUmUsuario_passandoEmailJaCadastradoPorOutroUsuario_deveRetornarException() {
        var request = new AtualizarUsuarioRequest();
        request.setEmail("email@teste.com");
        when(repository.existsByEmailAndCpfIsNot(eq(request.getEmail()), anyString())).thenReturn(true);

        assertThrows(EmailJaCadastradoParaOutroUsuarioException.class,
                () -> service.alterarUmUsuario("12345678901", request));
    }

    @Test
    void alterarUmUsuario_semAlterarEnderecoComSucesso() {
        var request = AtualizarUsuarioRequestFactory.criaRequest();
        request.setEndereco(null);
        var cpf = "12345678901";
        when(repository.existsByEmailAndCpfIsNot(request.getEmail(), cpf)).thenReturn(false);
        var usuarioCadastrado = UsuarioFactory.criaUsuario();
        when(repository.findByCpf(cpf)).thenReturn(Optional.of(usuarioCadastrado));
        when(repository.save(any(Usuario.class))).thenReturn(new Usuario());

        service.alterarUmUsuario(cpf, request);

        verify(repository).save(captor.capture());
        var usuarioAlterado = captor.getValue();
        assertEquals(request.getNome(), usuarioAlterado.getNome());
        assertEquals(request.getEmail(), usuarioAlterado.getEmail());
        assertEquals(request.getSenha(), usuarioAlterado.getSenha());
        assertEquals(request.getDataNascimento(), usuarioAlterado.getDataNascimento());
        assertEquals(usuarioCadastrado.getEndereco(), usuarioAlterado.getEndereco());
    }

    @Test
    void alterarUmUsuario_novoEnderecoComSucesso() {
        var request = AtualizarUsuarioRequestFactory.criaRequest();
        var cpf = "12345678901";
        when(repository.existsByEmailAndCpfIsNot(request.getEmail(), cpf)).thenReturn(false);
        when(repository.findByCpf(cpf)).thenReturn(Optional.of(UsuarioFactory.criaUsuario()));
        when(repository.save(any(Usuario.class))).thenReturn(new Usuario());

        service.alterarUmUsuario(cpf, request);

        verify(repository).save(captor.capture());
        var usuarioCapturado = captor.getValue();
        assertEquals(request.getEndereco(), usuarioCapturado.getEndereco());
    }
}
