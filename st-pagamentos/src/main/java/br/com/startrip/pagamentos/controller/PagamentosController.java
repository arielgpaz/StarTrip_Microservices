package br.com.startrip.pagamentos.controller;

import br.com.startrip.pagamentos.domain.PagamentosReserva;
import br.com.startrip.pagamentos.domain.request.PagamentoRequest;
import br.com.startrip.pagamentos.service.CancelarReservaService;
import br.com.startrip.pagamentos.service.EstornarReservaService;
import br.com.startrip.pagamentos.service.PagamentosService;
import br.com.startrip.pagamentos.service.PagarReservaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
public class PagamentosController {

    private final PagamentosService pagamentosService;

    private final PagarReservaService pagarReservaService;

    private final CancelarReservaService cancelarReservaService;

    private final EstornarReservaService estornarReservaService;

    @GetMapping("/reserva/id/{idReserva}")
    public PagamentosReserva listarPagamentosReserva(
            @PathVariable String idReserva) {

        return pagamentosService.listarPagamentosReserva(idReserva);
    }

    @PutMapping("/pagamento/id/{idReserva}")
    public void pagarReserva(
            @PathVariable String idReserva,
            @RequestBody PagamentoRequest pagamentoRequest) {
        pagarReservaService.pagarReserva(idReserva, pagamentoRequest);
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
