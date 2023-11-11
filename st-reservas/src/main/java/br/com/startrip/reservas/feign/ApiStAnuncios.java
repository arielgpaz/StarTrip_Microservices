package br.com.startrip.reservas.feign;

import br.com.startrip.reservas.domain.Anuncio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ApiStAnuncios", url = "${reservas.urls.api-st-anuncios}")
public interface ApiStAnuncios {

    @GetMapping("/id/{idAnuncio}")
    Optional<Anuncio> buscarAnuncioPorId(@PathVariable String idAnuncio);

}
