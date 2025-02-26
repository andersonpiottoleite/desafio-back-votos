package br.com.anderson.southsystem.desafiobackvotos.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.service.PautaServiceImpl;
import br.com.anderson.southsystem.desafiobackvotos.vo.PautaVO;
import io.swagger.annotations.ApiOperation;

/** Classe que representa um controller para tratar informações de <code>Pauta</code>
 * 
 * @author Anderson Piotto
 * @since 08/10/2021
 * @version 1.0.0
 */

@RestController
@RequestMapping("/v1/pauta")
public class PautaController {
	
	@Autowired
	private PautaServiceImpl pautaService;
	
	@ApiOperation("Salva uma nova pauta")
	@PostMapping
	public ResponseEntity<PautaVO> salvar(@RequestBody @Valid PautaDTO pautaDTO, UriComponentsBuilder uriBuilder) throws DesafioBackVotosException{
		Pauta pauta = pautaService.salvar(pautaDTO);
		URI uri = uriBuilder.path("/v1/pauta/{idPauta}").buildAndExpand(pauta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PautaVO(pauta));
	}
	
	@ApiOperation("Busca uma pauta pelo id")
	@GetMapping("/{idPauta}")
	public ResponseEntity<PautaVO> buscar(@PathVariable("idPauta") Long idPauta) throws DesafioBackVotosException{
		Pauta pauta = pautaService.buscar(idPauta);
		return ResponseEntity.ok(new PautaVO(pauta));
	}
}
