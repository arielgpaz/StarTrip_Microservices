package br.com.startrip.usuarios.service;

import br.com.startrip.usuarios.controller.request.AtualizarUsuarioRequest;
import br.com.startrip.usuarios.domain.Usuario;
import br.com.startrip.usuarios.exception.CpfJaCadastradoParaOutroUsuarioException;
import br.com.startrip.usuarios.exception.CpfUsuarioInexistenteException;
import br.com.startrip.usuarios.exception.EmailJaCadastradoParaOutroUsuarioException;
import br.com.startrip.usuarios.exception.IdUsuarioInexistenteException;
import br.com.startrip.usuarios.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EmailJaCadastradoParaOutroUsuarioException(usuario.getEmail());
        }

        if (usuarioRepository.existsByCpf(usuario.getCpf())) {
            throw new CpfJaCadastradoParaOutroUsuarioException(usuario.getCpf());
        }

        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario buscarUsuarioPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IdUsuarioInexistenteException(id));
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new CpfUsuarioInexistenteException(cpf));
    }

    public Usuario alterarUmUsuario(String cpf, AtualizarUsuarioRequest atualizarUsuarioRequest) {

        if (usuarioRepository.existsByEmailAndCpfIsNot(atualizarUsuarioRequest.getEmail(), cpf)) {
            throw new EmailJaCadastradoParaOutroUsuarioException(atualizarUsuarioRequest.getEmail());
        }

        Usuario usuarioEncontrado = this.buscarUsuarioPorCpf(cpf);

        Usuario usuarioAtualizado = new Usuario();
        BeanUtils.copyProperties(usuarioEncontrado, usuarioAtualizado);

        usuarioAtualizado.setNome(atualizarUsuarioRequest.getNome());
        usuarioAtualizado.setEmail(atualizarUsuarioRequest.getEmail());
        usuarioAtualizado.setSenha(atualizarUsuarioRequest.getSenha());
        usuarioAtualizado.setDataNascimento(atualizarUsuarioRequest.getDataNascimento());

        if (atualizarUsuarioRequest.getEndereco() != null) {
            usuarioAtualizado.setEndereco(atualizarUsuarioRequest.getEndereco());
        }

        return usuarioRepository.save(usuarioAtualizado);
    }
}
