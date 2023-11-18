package br.com.startrip.reservas.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DadosSolicitanteResponse {

    private String id;

    private String nome;
}
