package br.com.startrip.reservas.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Usuario {

    private String id;

    private String nome;

    private String email;

    private String cpf;

    private LocalDate dataNascimento;

    private Endereco endereco;

    private String foto;

}
