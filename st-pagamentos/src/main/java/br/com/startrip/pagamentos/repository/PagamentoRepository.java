package br.com.startrip.pagamentos.repository;

import br.com.startrip.pagamentos.domain.MensagemPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends MongoRepository<MensagemPagamento, String> {
    List<MensagemPagamento> findByIdReserva(String idReserva);
}
