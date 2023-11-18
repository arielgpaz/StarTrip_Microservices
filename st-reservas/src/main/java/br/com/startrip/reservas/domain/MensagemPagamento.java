package br.com.startrip.reservas.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensagemPagamento {

    private String id;

    private String idReserva;

    private Pagamento pagamento;

    private StatusMensagem status;

}
