package br.com.startrip.reservas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {

    private String id;

    private String identificacao;

    private TipoImovel tipoImovel;

    private Endereco endereco;

    private Usuario proprietario;

    private List<CaracteristicaImovel> caracteristicas;

}
