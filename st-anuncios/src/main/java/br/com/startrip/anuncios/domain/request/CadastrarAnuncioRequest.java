package br.com.startrip.anuncios.domain.request;

import br.com.startrip.anuncios.domain.FormaPagamento;
import br.com.startrip.anuncios.domain.TipoAnuncio;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CadastrarAnuncioRequest {

	@NotNull
	private String idImovel;

	@NotNull
	private String cpfAnunciante;

	@NotNull
	private TipoAnuncio tipoAnuncio;

	@NotNull
	private BigDecimal valorDiaria;

	@NotNull
	private List<FormaPagamento> formasAceitas;

	@NotEmpty
	private String descricao;
}
