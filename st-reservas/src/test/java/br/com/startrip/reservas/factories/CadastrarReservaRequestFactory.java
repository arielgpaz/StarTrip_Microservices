package br.com.startrip.reservas.factories;

import br.com.startrip.reservas.domain.request.CadastrarReservaRequest;

public class CadastrarReservaRequestFactory {
    public static CadastrarReservaRequest criarRequest() {
        return CadastrarReservaRequest.builder()
                .cpfSolicitante("12345678902")
                .idAnuncio("1a2b23k")
                .periodo(PeriodoFactory.criaPeriodo())
                .quantidadePessoas(3)
                .build();
    }
}
