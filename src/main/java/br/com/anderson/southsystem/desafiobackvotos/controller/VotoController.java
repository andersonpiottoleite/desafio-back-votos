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

import br.com.anderson.southsystem.desafiobackvotos.dto.VotoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;
import br.com.anderson.southsystem.desafiobackvotos.service.VotoServiceImpl;
import br.com.anderson.southsystem.desafiobackvotos.vo.SessaoVotacaoVO;
import br.com.anderson.southsystem.desafiobackvotos.vo.VotoVO;
import io.swagger.annotations.ApiOperation;

/** Classe que representa um controller para tratar informações de <code>Voto</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@RestController
@RequestMapping("/voto")
public class VotoController {
	
	@Autowired
	private VotoServiceImpl votoService;
	
	@ApiOperation("Salva um novo voto")
	@PostMapping("/salvar")
	//public ResponseEntity<VotoVO> salvar(@RequestBody @Valid VotoDTO votoDTO, UriComponentsBuilder uriBuilder) throws DesafioBackVotosException{
	public ResponseEntity<SessaoVotacaoVO> salvar(@RequestBody @Valid VotoDTO votoDTO, UriComponentsBuilder uriBuilder) throws DesafioBackVotosException{
		Voto voto = votoService.salvar(votoDTO);
		URI uri = uriBuilder.path("/associado/{idAssociado}").buildAndExpand(voto.getId()).toUri();
        //return ResponseEntity.created(uri).body(new VotoVO(voto));
		return ResponseEntity.created(uri).body(new SessaoVotacaoVO(voto));
	}
	
	@ApiOperation("Busca um voto pelo id")
	@GetMapping("/{idVoto}")
	//public ResponseEntity<VotoVO> buscar(@PathVariable("idVoto") Long idVoto) throws DesafioBackVotosException{
	public ResponseEntity<SessaoVotacaoVO> buscar(@PathVariable("idVoto") Long idVoto) throws DesafioBackVotosException{
		Voto voto = votoService.buscar(idVoto);
		//return ResponseEntity.ok(new VotoVO(voto));
		return ResponseEntity.ok(new SessaoVotacaoVO(voto));
	}

}
