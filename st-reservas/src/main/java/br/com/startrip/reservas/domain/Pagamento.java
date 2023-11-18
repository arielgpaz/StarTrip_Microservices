package br.com.startrip.reservas.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Pagamento {

    private BigDecimal valorTotal;

    private FormaPagamento formaEscolhida;

    private StatusPagamento status;

    private LocalDateTime data;

}
