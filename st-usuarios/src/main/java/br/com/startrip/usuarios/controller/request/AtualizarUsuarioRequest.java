package br.com.startrip.usuarios.controller.request;

import br.com.startrip.usuarios.domain.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizarUsuarioRequest {

	@NotEmpty
	private String nome;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String senha;

	@NotNull
	private LocalDate dataNascimento;

	@Valid
	private Endereco endereco;

}
