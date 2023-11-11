package br.com.startrip.anuncios.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "deleted" }, allowSetters = true)
@Document(collection = "anuncios")
public class Anuncio {

	@Id
	private String id;

	private TipoAnuncio tipoAnuncio;

	private Imovel imovel;

	private Usuario anunciante;

	private BigDecimal valorDiaria;

	private List<FormaPagamento> formasAceitas;

	private String descricao;

	@JsonProperty("deleted")
	private boolean deleted;

}
