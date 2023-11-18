package br.com.startrip.imoveis.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Endereco {

	@NotNull
	@Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ser informado no formato 99999-999.")
	private String cep;

	@NotNull
	private String logradouro;

	@NotNull
	private String numero;

	private String complemento;

	@NotNull
	private String bairro;

	@NotNull
	private String cidade;

	@NotNull
	private String estado;

}
