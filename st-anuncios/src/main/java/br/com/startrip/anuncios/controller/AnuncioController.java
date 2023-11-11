package br.com.startrip.anuncios.controller;

import br.com.startrip.anuncios.controller.request.CadastrarAnuncioRequest;
import br.com.startrip.anuncios.domain.Anuncio;
import br.com.startrip.anuncios.service.AnuncioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio anunciarImovel(
            @RequestBody @Valid CadastrarAnuncioRequest cadastrarAnuncioRequest) {
        return anuncioService.anunciarImovel(cadastrarAnuncioRequest);
    }

    @GetMapping
    public Page<Anuncio> listarAnuncios(
            @PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC) Pageable pageable) {
        return anuncioService.listarAnuncios(pageable);
    }

    @GetMapping("/anunciante/cpf/{cpfAnunciante}")
    public Page<Anuncio> listarAnunciosDeAnunciante(
            @PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable String cpfAnunciante) {
        return anuncioService.listarAnunciosDeAnunciante(pageable, cpfAnunciante);
    }

    @DeleteMapping("/{idAnuncio}")
    public void excluirAnuncio(
            @PathVariable String idAnuncio) {
        anuncioService.excluirAnuncio(idAnuncio);
    }

    @GetMapping("/ativos/imovel/{idImovel}")
    public boolean existeAnuncioParaImovel(
            @PathVariable String idImovel) {
        return anuncioService.verificarSeExisteAnuncioParaImovel(idImovel);
    }

    @GetMapping("/id/{idAnuncio}")
    public Anuncio buscarAnuncioPorId(
            @PathVariable String idAnuncio) {
        return anuncioService.buscarAnuncioPorId(idAnuncio);
    }
}
