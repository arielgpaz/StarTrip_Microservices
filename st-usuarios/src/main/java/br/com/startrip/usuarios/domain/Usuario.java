package br.com.startrip.usuarios.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@JsonIgnoreProperties(value = { "senha" }, allowSetters = true)
@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;

	@NotEmpty
	private String nome;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@JsonProperty("senha")
	private String senha;

	@NotEmpty
	@Pattern(regexp = "\\d{11}", message = "O CPF deve ser informado no formato 99999999999.")
	private String cpf;

	@NotNull
	private LocalDate dataNascimento;

	@Valid
	private Endereco endereco;

	private String foto;

}
