package br.com.startrip.reservas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
