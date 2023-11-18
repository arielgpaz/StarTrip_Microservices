package br.com.startrip.pagamentos.domain.request;

import br.com.startrip.pagamentos.domain.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoRequest {

    private FormaPagamento formaPagamento;

    private BigDecimal valor;
}
