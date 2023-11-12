package br.com.startrip.pagamentos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pagamentos")
public class MensagemPagamento {

    @Id
    private String id;

    private String idReserva;

    private PagamentoReserva pagamento;

}
