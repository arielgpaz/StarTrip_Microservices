package br.com.startrip.reservas.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Endereco {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

}
