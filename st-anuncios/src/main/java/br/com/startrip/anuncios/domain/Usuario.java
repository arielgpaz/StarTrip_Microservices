package br.com.startrip.anuncios.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
