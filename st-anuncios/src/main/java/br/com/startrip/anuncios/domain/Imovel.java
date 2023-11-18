package br.com.startrip.anuncios.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Imovel {

    private String id;

    private String identificacao;

    private TipoImovel tipoImovel;

    private Endereco endereco;

    private Usuario proprietario;

    private List<CaracteristicaImovel> caracteristicas;

}
