package br.com.startrip.imoveis.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "StAnuncios", url = "${imoveis.urls.api-st-anuncios}")
public interface ApiStAnuncios {

    @GetMapping("/ativos/imovel/{idImovel}")
    boolean existeAnuncioParaImovel(@PathVariable String idImovel);
}
