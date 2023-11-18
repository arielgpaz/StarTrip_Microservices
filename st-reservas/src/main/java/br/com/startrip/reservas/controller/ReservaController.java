package br.com.startrip.reservas.controller;

import br.com.startrip.reservas.domain.Periodo;
import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.domain.request.CadastrarReservaRequest;
import br.com.startrip.reservas.domain.response.InformacaoReservaResponse;
import br.com.startrip.reservas.service.*;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@AllArgsConstructor
public class ReservaController {

    private final RealizarReservaService realizarReservaService;

    private final ListarReservaSolicitanteService listarReservaSolicitanteService;

    private final ListarReservaAnuncianteService listarReservaAnuncianteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoReservaResponse realizarUmaReserva(
            @RequestBody CadastrarReservaRequest cadastrarReservaRequest) {
        return realizarReservaService.realizarUmaReserva(cadastrarReservaRequest);
    }

    @GetMapping("/solicitante/id/{idSolicitante}")
    public Page<Reserva> listarReservaSolicitante(
            @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable String idSolicitante,
            @ParameterObject Periodo periodo) {
        return listarReservaSolicitanteService.listarReservaDeSolicitante(pageable, idSolicitante, periodo);
    }

    @GetMapping("/anunciante/cpf/{cpfAnunciante}")
    public Page<Reserva> listarReservaDeAnunciante(
            @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable String cpfAnunciante) {
        return listarReservaAnuncianteService.listarReservaDeAnunciante(pageable, cpfAnunciante);
    }

}
