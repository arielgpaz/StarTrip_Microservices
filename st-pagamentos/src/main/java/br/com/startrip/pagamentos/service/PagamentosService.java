package br.com.startrip.pagamentos.service;

import br.com.startrip.pagamentos.domain.MensagemPagamento;
import br.com.startrip.pagamentos.domain.PagamentoReserva;
import br.com.startrip.pagamentos.domain.PagamentosReserva;
import br.com.startrip.pagamentos.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class PagamentosService {
    
    private final PagamentoRepository pagamentoRepository;


    public PagamentosReserva listarPagamentosReserva(String idReserva) {

        List<MensagemPagamento> mensagensPagamentos = pagamentoRepository.findByIdReserva(idReserva);

        List<PagamentoReserva> pagamentos = mensagensPagamentos.stream()
                .map(MensagemPagamento::getPagamento)
                .sorted(Comparator.comparing(PagamentoReserva::getData))
                .toList();

        return PagamentosReserva.builder()
                .idReserva(idReserva)
                .pagamentos(pagamentos)
                .build();
    }
}
