package br.com.startrip.pagamentos.service;


import br.com.startrip.pagamentos.domain.MensagemPagamento;
import br.com.startrip.pagamentos.domain.PagamentoReserva;
import br.com.startrip.pagamentos.domain.StatusPagamento;
import br.com.startrip.pagamentos.producer.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CancelarReservaService {

    private final KafkaProducer kafkaProducer;

    public void cancelarReserva(final String idReserva) {

        PagamentoReserva pagamento = PagamentoReserva.builder()
                .status(StatusPagamento.CANCELADO)
                .data(LocalDateTime.now())
                .build();

        MensagemPagamento mensagemPagamento = MensagemPagamento.builder()
                .idReserva(idReserva)
                .pagamento(pagamento)
                .build();

        kafkaProducer.enviarMensagem(mensagemPagamento);

    }

}
