package br.com.startrip.anuncios.service;

import br.com.startrip.anuncios.controller.request.CadastrarAnuncioRequest;
import br.com.startrip.anuncios.domain.Anuncio;
import br.com.startrip.anuncios.domain.Imovel;
import br.com.startrip.anuncios.domain.Usuario;
import br.com.startrip.anuncios.exceptions.CpfUsuarioInexistenteException;
import br.com.startrip.anuncios.exceptions.IdAnuncioNaoEncontradoException;
import br.com.startrip.anuncios.exceptions.IdImovelInexistenteException;
import br.com.startrip.anuncios.exceptions.ImovelJaAnunciadoException;
import br.com.startrip.anuncios.feign.ApiStImoveis;
import br.com.startrip.anuncios.feign.ApiStUsuarios;
import br.com.startrip.anuncios.repository.AnuncioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    private final ApiStImoveis apiStImoveis;

    private final ApiStUsuarios apiStUsuarios;

    private final AnuncioRepository anuncioRepository;

    public AnuncioService(ApiStImoveis apiStImoveis, ApiStUsuarios apiStUsuarios, AnuncioRepository anuncioRepository) {
        this.apiStImoveis = apiStImoveis;
        this.apiStUsuarios = apiStUsuarios;
        this.anuncioRepository = anuncioRepository;
    }

    public Anuncio anunciarImovel(CadastrarAnuncioRequest cadastrarAnuncioRequest) {
        Imovel imovelParaCadastrar = apiStImoveis.buscarImovelPorId(cadastrarAnuncioRequest.getIdImovel())
                .orElseThrow(() -> new IdImovelInexistenteException(cadastrarAnuncioRequest.getIdImovel()));

        if (anuncioRepository.existsByImovelIdAndDeletedIs(cadastrarAnuncioRequest.getIdImovel(), false)) {
            throw new ImovelJaAnunciadoException(cadastrarAnuncioRequest.getIdImovel());
        }

        Usuario anunciante = apiStUsuarios.buscarUsuarioPorCpf(cadastrarAnuncioRequest.getCpfAnunciante())
                .orElseThrow(() -> new CpfUsuarioInexistenteException(cadastrarAnuncioRequest.getCpfAnunciante()));

        return anuncioRepository.save(Anuncio.builder()
                .tipoAnuncio(cadastrarAnuncioRequest.getTipoAnuncio())
                .imovel(imovelParaCadastrar)
                .anunciante(anunciante)
                .valorDiaria(cadastrarAnuncioRequest.getValorDiaria())
                .formasAceitas(cadastrarAnuncioRequest.getFormasAceitas())
                .descricao(cadastrarAnuncioRequest.getDescricao())
                .build());
    }

    public Page<Anuncio> listarAnuncios(Pageable pageable) {
        return anuncioRepository.findByDeletedIs(false, pageable);
    }

    public Page<Anuncio> listarAnunciosDeAnunciante(Pageable pageable, String cpfAnunciante) {
        Usuario usuario = apiStUsuarios.buscarUsuarioPorCpf(cpfAnunciante)
                .orElseThrow(() -> new CpfUsuarioInexistenteException(cpfAnunciante));
        return anuncioRepository.findByAnuncianteAndDeletedIs(pageable, usuario, false);
    }

    public void excluirAnuncio(String idAnuncio) {
        Anuncio anuncioParaExcluir = anuncioRepository.findByIdAndDeletedIs(idAnuncio, false)
                .orElseThrow(() -> new IdAnuncioNaoEncontradoException(idAnuncio));
        anuncioParaExcluir.setDeleted(true);
        anuncioRepository.save(anuncioParaExcluir);
    }

    public boolean verificarSeExisteAnuncioParaImovel(String idImovel) {
        return anuncioRepository.existsByImovelIdAndDeletedIs(idImovel, false);
    }

    public Anuncio buscarAnuncioPorId(String idAnuncio) {
        return anuncioRepository.findByIdAndDeletedIs(idAnuncio, false)
                .orElseThrow(() -> new IdAnuncioNaoEncontradoException(idAnuncio));
    }
}
