package br.com.startrip.imoveis.controller;

import br.com.startrip.imoveis.domain.Imovel;
import br.com.startrip.imoveis.domain.request.CadastrarImovelRequest;
import br.com.startrip.imoveis.service.ImovelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/imoveis")
public class ImovelController {

	private final ImovelService imovelService;

	public ImovelController(ImovelService imovelService) {
		this.imovelService = imovelService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Imovel cadastrarImovel(
			@RequestBody @Valid CadastrarImovelRequest cadastrarImovelRequest) {
		return imovelService.cadastrarImovel(cadastrarImovelRequest);
	}

	@GetMapping
	public Page<Imovel> listarImoveis(
			@PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC) Pageable pageable) {
		return imovelService.listarImoveis(pageable);
	}

	@GetMapping("/proprietarios/cpf/{cpfProprietario}")
	public Page<Imovel> listarImoveisDeProprietario(
			@PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC) Pageable pageable,
			@PathVariable String cpfProprietario) {
		return imovelService.listarImoveisDeProprietario(pageable, cpfProprietario);
	}

	@GetMapping("/id/{idImovel}")
	public Imovel buscarImovelPorId(
			@PathVariable String idImovel) {
		return imovelService.buscarImovelPorId(idImovel);
	}

	@DeleteMapping("/id/{idImovel}")
	public void excluirImovel(
			@PathVariable String idImovel) {
		imovelService.excluirImovel(idImovel);
	}
}
