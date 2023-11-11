package br.com.startrip.reservas.feign;

import br.com.startrip.reservas.domain.Imovel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "StImoveis", url = "${reservas.urls.api-st-imoveis}")
public interface ApiStImoveis {

    @GetMapping("/id/{idImovel}")
    Optional<Imovel> listarImoveisPorId(@PathVariable String idImovel);
}
