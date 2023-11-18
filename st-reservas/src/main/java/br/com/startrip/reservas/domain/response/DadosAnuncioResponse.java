package br.com.startrip.reservas.domain.response;

import br.com.startrip.reservas.domain.FormaPagamento;
import br.com.startrip.reservas.domain.Imovel;
import br.com.startrip.reservas.domain.Usuario;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DadosAnuncioResponse {

    private String id;

    private Imovel imovel;

    private Usuario anunciante;

    private List<FormaPagamento> formasAceitas;

    private String descricao;

}
