package br.com.startrip.pagamentos.producer;

import br.com.startrip.pagamentos.domain.MensagemPagamento;
import br.com.startrip.pagamentos.domain.StatusMensagem;
import br.com.startrip.pagamentos.repository.PagamentoRepository;
import br.com.startrip.pagamentos.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    @Value("${pagamentos.kafka.topic-pagamentos}")
    private String pagamentosTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final PagamentoRepository pagamentoRepository;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, PagamentoRepository pagamentoRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.pagamentoRepository = pagamentoRepository;
    }

    public void enviarMensagem(MensagemPagamento mensagemPagamento) {

        try {
            kafkaTemplate.send(
                    pagamentosTopic,
                    mensagemPagamento.getIdReserva(),
                    JsonUtil.objectToJson(mensagemPagamento));

            mensagemPagamento.setStatus(StatusMensagem.ENVIADA);

        } catch (Exception e) {
            mensagemPagamento.setStatus(StatusMensagem.ERRO);
            log.error("Não foi possível enviar a mensagem: {}.", mensagemPagamento, e);
        } finally {
            pagamentoRepository.save(mensagemPagamento);
        }
    }


}
