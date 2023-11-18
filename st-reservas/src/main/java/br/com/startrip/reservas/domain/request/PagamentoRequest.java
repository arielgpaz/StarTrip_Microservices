package br.com.startrip.reservas.domain.request;

import br.com.startrip.reservas.domain.FormaPagamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PagamentoRequest {

    private FormaPagamento formaPagamento;

    private BigDecimal valor;
}
