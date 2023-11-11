package br.com.startrip.anuncios.factories;

import br.com.startrip.anuncios.controller.request.CadastrarAnuncioRequest;
import br.com.startrip.anuncios.domain.FormaPagamento;
import br.com.startrip.anuncios.domain.TipoAnuncio;

import java.math.BigDecimal;
import java.util.Arrays;

public class CadastrarAnuncioRequestFactory {
    public static CadastrarAnuncioRequest criarRequest() {
        return CadastrarAnuncioRequest.builder()
                .idImovel("1a2b3c4d")
                .cpfAnunciante("12345678901")
                .tipoAnuncio(TipoAnuncio.QUARTO)
                .valorDiaria(BigDecimal.valueOf(100))
                .formasAceitas(Arrays.asList(FormaPagamento.DINHEIRO, FormaPagamento.CARTAO_CREDITO, FormaPagamento.CARTAO_DEBITO))
                .descricao("Quarto com sacada de frente para a praia")
                .build();
    }
}
