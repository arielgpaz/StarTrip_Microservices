package br.com.startrip.reservas.exceptions;

import br.com.startrip.reservas.domain.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaPagamentoException extends RuntimeException {
    public FormaPagamentoException(FormaPagamento formaPagamento, List<FormaPagamento> formasAceitas) {
        super(String.format("O anúncio não aceita %s como forma de pagamento. As formas aceitas são %s.", formaPagamento, formasAceitas)
                .replaceAll("[\\p{Ps}\\p{Pe}]", ""));
    }
}
