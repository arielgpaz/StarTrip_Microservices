package br.com.startrip.reservas.feign;

import br.com.startrip.reservas.domain.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "StUsuarios", url = "${reservas.urls.api-st-usuarios}")
public interface ApiStUsuarios {

    @GetMapping(value = "/cpf/{cpf}")
    Optional<Usuario> buscarUsuarioPorCpf(@PathVariable String cpf);

}
