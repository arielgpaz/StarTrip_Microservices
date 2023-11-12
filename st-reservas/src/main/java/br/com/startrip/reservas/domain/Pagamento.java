package br.com.startrip.reservas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagamento {

    private BigDecimal valorTotal;

    private FormaPagamento formaEscolhida;

    private StatusPagamento status;

    private LocalDateTime data;

}
