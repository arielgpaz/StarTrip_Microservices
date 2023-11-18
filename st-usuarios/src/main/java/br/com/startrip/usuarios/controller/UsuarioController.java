package br.com.startrip.usuarios.controller;


import br.com.startrip.usuarios.domain.Usuario;
import br.com.startrip.usuarios.domain.request.AtualizarUsuarioRequest;
import br.com.startrip.usuarios.domain.request.CadastrarUsuarioRequest;
import br.com.startrip.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cadastrarUsuario(
			@RequestBody @Valid CadastrarUsuarioRequest usuarioRequest) {

		Usuario usuario = Usuario.builder().build();
		BeanUtils.copyProperties(usuarioRequest, usuario);

		return usuarioService.cadastrar(usuario);
	}

	@GetMapping
	public Page<Usuario> listarUsuarios(
			@PageableDefault(sort = "nome") Pageable pageable) {
		return usuarioService.listarUsuarios(pageable);
	}

	@GetMapping("/id/{idUsuario}")
	public Usuario buscarUsuarioPorId(
			@PathVariable String idUsuario) {
		return usuarioService.buscarUsuarioPorId(idUsuario);
	}

	@GetMapping("/cpf/{cpf}")
	public Usuario buscarUsuarioPorCpf(
			@PathVariable String cpf) {
		return usuarioService.buscarUsuarioPorCpf(cpf);
	}

	@PutMapping("/cpf/{cpf}")
	public Usuario alterarUmUsuario(
			@PathVariable String cpf,
			@RequestBody @Valid AtualizarUsuarioRequest atualizarUsuarioRequest) {
		return usuarioService.alterarUmUsuario(cpf, atualizarUsuarioRequest);
	}

}
