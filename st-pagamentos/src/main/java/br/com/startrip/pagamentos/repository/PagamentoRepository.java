package br.com.startrip.pagamentos.repository;

import br.com.startrip.pagamentos.domain.MensagemPagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends MongoRepository<MensagemPagamento, String> {
}
