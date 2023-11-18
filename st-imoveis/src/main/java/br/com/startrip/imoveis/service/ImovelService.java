package br.com.startrip.imoveis.service;

import br.com.startrip.imoveis.domain.Imovel;
import br.com.startrip.imoveis.domain.Usuario;
import br.com.startrip.imoveis.domain.request.CadastrarImovelRequest;
import br.com.startrip.imoveis.exceptions.IdImovelInexistenteException;
import br.com.startrip.imoveis.exceptions.ImovelPossuiAnuncioException;
import br.com.startrip.imoveis.exceptions.ProprietarioNaoEncontradoPeloCpfException;
import br.com.startrip.imoveis.feign.ApiStAnuncios;
import br.com.startrip.imoveis.feign.ApiStUsuarios;
import br.com.startrip.imoveis.repository.ImovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    private final ImovelRepository imovelRepository;

    private final ApiStUsuarios apiStUsuarios;

    private final ApiStAnuncios apiStAnuncios;

    public ImovelService(ImovelRepository imovelRepository, ApiStUsuarios apiStUsuarios, ApiStAnuncios apiStAnuncios) {
        this.imovelRepository = imovelRepository;
        this.apiStUsuarios = apiStUsuarios;
        this.apiStAnuncios = apiStAnuncios;
    }

    public Imovel cadastrarImovel(CadastrarImovelRequest cadastrarImovelRequest) {

        Usuario proprietario = apiStUsuarios.buscarUsuarioPorCpf(cadastrarImovelRequest.getCpfProprietario())
                .orElseThrow(() -> new ProprietarioNaoEncontradoPeloCpfException(cadastrarImovelRequest.getCpfProprietario()));

        return imovelRepository.save(Imovel.builder()
                .identificacao(cadastrarImovelRequest.getIdentificacao())
                .tipoImovel(cadastrarImovelRequest.getTipoImovel())
                .endereco(cadastrarImovelRequest.getEndereco())
                .proprietario(proprietario)
                .caracteristicas(cadastrarImovelRequest.getCaracteristicas())
                .build());
    }

    public Page<Imovel> listarImoveis(Pageable pageable) {
        return imovelRepository.findByDeletedIs(false, pageable);
    }

    public Page<Imovel> listarImoveisDeProprietario(Pageable pageable, String cpfProprietario) {
        return imovelRepository.findByProprietarioCpfEqualsAndDeletedIs(pageable, cpfProprietario, false);
    }

    public Imovel buscarImovelPorId(String idImovel) {
        return imovelRepository.findByIdAndDeletedIs(idImovel, false)
                .orElseThrow(() -> new IdImovelInexistenteException(idImovel));
    }

    public void excluirImovel(String idImovel) {
        Imovel imovelParaExcluir = this.buscarImovelPorId(idImovel);

        if (apiStAnuncios.existeAnuncioParaImovel(imovelParaExcluir.getId())) {
            throw new ImovelPossuiAnuncioException();
        }

        imovelParaExcluir.setDeleted(true);
        imovelRepository.save(imovelParaExcluir);
    }
}
