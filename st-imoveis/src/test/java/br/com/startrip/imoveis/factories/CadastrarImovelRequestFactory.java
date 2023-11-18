package br.com.startrip.imoveis.factories;

import br.com.startrip.imoveis.domain.CaracteristicaImovel;
import br.com.startrip.imoveis.domain.TipoImovel;
import br.com.startrip.imoveis.domain.request.CadastrarImovelRequest;

import java.util.List;

public class CadastrarImovelRequestFactory {
    public static CadastrarImovelRequest criarRequest() {
        return CadastrarImovelRequest.builder()
                .identificacao("Hotel dos romeiros")
                .tipoImovel(TipoImovel.HOTEL)
                .endereco(EnderecoFactory.criaEndereco())
                .cpfProprietario("12345678901")
                .caracteristicas(List.of(CaracteristicaImovel.builder()
                                .descricao("5 estrelas")
                        .build()))
                .build();
    }
}
