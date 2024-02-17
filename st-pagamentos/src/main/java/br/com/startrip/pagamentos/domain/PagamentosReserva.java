package br.com.startrip.pagamentos.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagamentosReserva {

    private String idReserva;

    private List<PagamentoReserva> pagamentos;
}
