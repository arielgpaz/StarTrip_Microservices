package br.com.startrip.pagamentos.domain.request;

import br.com.startrip.pagamentos.domain.FormaPagamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PagamentoRequest {

    private FormaPagamento formaPagamento;

    private BigDecimal valor;
}
