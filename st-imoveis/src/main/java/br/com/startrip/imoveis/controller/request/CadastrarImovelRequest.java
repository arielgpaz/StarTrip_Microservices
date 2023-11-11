package br.com.startrip.imoveis.controller.request;

import br.com.startrip.imoveis.domain.CaracteristicaImovel;
import br.com.startrip.imoveis.domain.Endereco;
import br.com.startrip.imoveis.domain.TipoImovel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarImovelRequest {

	@NotEmpty
	private String identificacao;

	@NotNull
	private TipoImovel tipoImovel;

	@NotNull
	@Valid
	private Endereco endereco;

	@NotNull
	private String cpfProprietario;

	private List<CaracteristicaImovel> caracteristicas;

}
