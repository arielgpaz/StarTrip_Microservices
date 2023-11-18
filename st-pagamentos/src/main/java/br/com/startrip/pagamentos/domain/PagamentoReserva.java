package br.com.startrip.pagamentos.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PagamentoReserva {

    private BigDecimal valorTotal;

    private FormaPagamento formaEscolhida;

    private StatusPagamento status;

    private LocalDateTime data;
}
