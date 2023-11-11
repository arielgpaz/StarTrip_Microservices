package br.com.startrip.anuncios.feign;

import br.com.startrip.anuncios.domain.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "StUsuarios", url = "${anuncios.urls.api-st-usuarios}")
public interface ApiStUsuarios {

    @GetMapping(value = "/cpf/{cpf}")
    Optional<Usuario> buscarUsuarioPorCpf(@PathVariable String cpf);

}
