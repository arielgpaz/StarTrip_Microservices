package br.com.startrip.reservas.domain.response;

import br.com.startrip.reservas.domain.Pagamento;
import br.com.startrip.reservas.domain.Periodo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacaoReservaResponse {

    private String idReserva;

    private DadosSolicitanteResponse solicitante;

    private Integer quantidadePessoas;

    private DadosAnuncioResponse anuncio;

    private Periodo periodo;

    private Pagamento pagamento;
}
