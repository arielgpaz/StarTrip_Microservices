package br.com.startrip.reservas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MensagemPagamento {

    private String id;

    private String idReserva;

    private Pagamento pagamento;

    private StatusMensagem status;

}
