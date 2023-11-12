package br.com.startrip.pagamentos.service;


import br.com.startrip.pagamentos.domain.MensagemPagamento;
import br.com.startrip.pagamentos.domain.PagamentoReserva;
import br.com.startrip.pagamentos.domain.StatusPagamento;
import br.com.startrip.pagamentos.domain.request.PagamentoRequest;
import br.com.startrip.pagamentos.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PagarReservaService {

    private final PagamentoRepository pagamentoRepository;

    public void pagarReserva(final String idReserva, PagamentoRequest pagamentoRequest) {

        PagamentoReserva pagamento = PagamentoReserva.builder()
                .formaEscolhida(pagamentoRequest.getFormaPagamento())
                .valorTotal(pagamentoRequest.getValor())
                .status(StatusPagamento.PAGO)
                .data(LocalDateTime.now())
                .build();

        MensagemPagamento mensagemPagamento = MensagemPagamento.builder()
                .idReserva(idReserva)
                .pagamento(pagamento)
                .build();

        kafkaProducer.send(mensagemPagamento);
        pagamentoRepository.save(mensagemPagamento);
    }
}
