package br.com.startrip.imoveis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "imoveis")
public class Imovel {

	@Id
	private String id;

	private String identificacao;

	private br.com.startrip.imoveis.domain.TipoImovel tipoImovel;

	private br.com.startrip.imoveis.domain.Endereco endereco;

	private br.com.startrip.imoveis.domain.Usuario proprietario;

	@ToString.Exclude
	private List<br.com.startrip.imoveis.domain.CaracteristicaImovel> caracteristicas;

	@JsonProperty("deleted")
	private boolean deleted;

}
