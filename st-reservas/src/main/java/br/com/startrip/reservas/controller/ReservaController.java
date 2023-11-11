package br.com.startrip.reservas.controller;

import br.com.startrip.reservas.controller.request.CadastrarReservaRequest;
import br.com.startrip.reservas.controller.response.InformacaoReservaResponse;
import br.com.startrip.reservas.domain.FormaPagamento;
import br.com.startrip.reservas.domain.Periodo;
import br.com.startrip.reservas.domain.Reserva;
import br.com.startrip.reservas.service.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final RealizarReservaService realizarReservaService;

    private final ListarReservaSolicitanteService listarReservaSolicitanteService;

    private final ListarReservaAnuncianteService listarReservaAnuncianteService;

    private final PagarReservaService pagarReservaService;

    private final CancelarReservaService cancelarReservaService;

    private final EstornarReservaService estornarReservaService;

    public ReservaController(RealizarReservaService realizarReservaService, ListarReservaSolicitanteService listarReservaSolicitanteService, ListarReservaAnuncianteService listarReservaAnuncianteService, PagarReservaService pagarReservaService, CancelarReservaService cancelarReservaService, EstornarReservaService estornarReservaService) {
        this.realizarReservaService = realizarReservaService;
        this.listarReservaSolicitanteService = listarReservaSolicitanteService;
        this.listarReservaAnuncianteService = listarReservaAnuncianteService;
        this.pagarReservaService = pagarReservaService;
        this.cancelarReservaService = cancelarReservaService;
        this.estornarReservaService = estornarReservaService;
    }

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

    @PutMapping("/pagamento/id/{idReserva}")
    public void pagarReserva(
            @PathVariable String idReserva,
            @RequestBody FormaPagamento formaPagamento) {
        pagarReservaService.pagarReserva(idReserva, formaPagamento);
    }

    @PutMapping("/cancelamento/id/{idReserva}")
    public void cancelarReserva(
            @PathVariable String idReserva) {
        cancelarReservaService.cancelarReserva(idReserva);
    }

    @PutMapping("/estorno/id/{idReserva}")
    public void estornarReserva(
            @PathVariable String idReserva) {
        estornarReservaService.estornarReserva(idReserva);
    }

}
