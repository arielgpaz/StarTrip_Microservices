package br.com.startrip.reservas.domain.request;

import br.com.startrip.reservas.domain.Periodo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarReservaRequest {

    @NotNull
    private String cpfSolicitante;

    @NotNull
    private String idAnuncio;

    @NotNull
    private Periodo periodo;

    @NotNull
    private Integer quantidadePessoas;

}
