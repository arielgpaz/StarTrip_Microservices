package br.com.startrip.anuncios.controller.request;

import br.com.startrip.anuncios.domain.FormaPagamento;
import br.com.startrip.anuncios.domain.TipoAnuncio;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
