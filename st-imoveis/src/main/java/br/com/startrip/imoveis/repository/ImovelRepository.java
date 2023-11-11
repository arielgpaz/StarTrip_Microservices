package br.com.startrip.imoveis.repository;

import br.com.startrip.imoveis.domain.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImovelRepository extends MongoRepository<Imovel, String> {

	Page<Imovel> findByProprietarioCpfEqualsAndDeletedIs(Pageable pageable, String cpfProprietario, boolean deleted);

	Optional<Imovel> findByIdAndDeletedIs(String id, boolean deleted);

	Page<Imovel> findByDeletedIs(boolean deleted, Pageable pageable);

}
