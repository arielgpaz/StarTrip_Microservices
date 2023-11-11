package br.com.startrip.anuncios.repository;


import br.com.startrip.anuncios.domain.Anuncio;
import br.com.startrip.anuncios.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnuncioRepository extends MongoRepository<Anuncio, Long> {

	Page<Anuncio> findByAnuncianteAndDeletedIs(Pageable pageable, Usuario anunciante, boolean deleted);

	boolean existsByImovelIdAndDeletedIs(String idImovel, boolean deleted);

	Page<Anuncio> findByDeletedIs(boolean deleted, Pageable pageable);

	Optional<Anuncio> findByIdAndDeletedIs(String idAnuncio, boolean deleted);
}
