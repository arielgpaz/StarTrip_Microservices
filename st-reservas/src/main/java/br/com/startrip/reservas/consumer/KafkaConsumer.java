package br.com.startrip.reservas.consumer;

import br.com.startrip.reservas.domain.MensagemPagamento;
import br.com.startrip.reservas.domain.Pagamento;
import br.com.startrip.reservas.exceptions.MensagemInvalidaException;
import br.com.startrip.reservas.service.CancelarReservaService;
import br.com.startrip.reservas.service.EstornarReservaService;
import br.com.startrip.reservas.service.PagarReservaService;
import br.com.startrip.reservas.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {

    private final PagarReservaService pagarReservaService;
    private final CancelarReservaService cancelarReservaService;
    private final EstornarReservaService estornarReservaService;

    @KafkaListener(
            topics = "${reservas.kafka.topic-pagamentos}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> mensagemRecebida, Acknowledgment acknowledgment) {

        MensagemPagamento mensagemPagamento;
        try {
            mensagemPagamento = JsonUtil.jsonToObject(mensagemRecebida.value(), MensagemPagamento.class);
        } catch (JsonProcessingException e) {
            throw new MensagemInvalidaException("Não foi possível ler a mensagem recebida.");
        }

        acknowledgment.acknowledge();

        String idReserva = mensagemPagamento.getIdReserva();
        Pagamento pagamento = mensagemPagamento.getPagamento();

        switch (pagamento.getStatus()) {
            case PAGO -> pagarReservaService.pagarReserva(idReserva, pagamento);
            case CANCELADO -> cancelarReservaService.cancelarReserva(idReserva, pagamento);
            case ESTORNADO -> estornarReservaService.estornarReserva(idReserva, pagamento);
            default -> log.info("A mensagem recebida não será tradada nesse fluxo.");
        }

    }

}
