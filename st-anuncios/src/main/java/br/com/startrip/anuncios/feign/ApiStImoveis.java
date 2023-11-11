package br.com.startrip.anuncios.feign;

import br.com.startrip.anuncios.domain.Imovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "StImoveis", url = "${anuncios.urls.api-st-imoveis}")
public interface ApiStImoveis {

    @GetMapping("/id/{idImovel}")
    Optional<Imovel> buscarImovelPorId(@PathVariable String idImovel);
}
