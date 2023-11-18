package br.com.startrip.reservas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@JsonIgnoreProperties(value = {"deleted"}, allowSetters = true)
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
