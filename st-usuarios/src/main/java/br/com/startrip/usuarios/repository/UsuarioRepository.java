package br.com.startrip.usuarios.repository;


import br.com.startrip.usuarios.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	boolean existsByEmail(String email);

	boolean existsByEmailAndCpfIsNot(String email, String cpf);

	Optional<Usuario> findByCpf(String cpf);

	boolean existsByCpf(String cpf);
}
