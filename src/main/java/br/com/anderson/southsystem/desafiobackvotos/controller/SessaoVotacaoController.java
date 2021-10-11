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

import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.service.SessaoVotacaoServiceImpl;
import br.com.anderson.southsystem.desafiobackvotos.vo.ResultadoVotosVO;
import br.com.anderson.southsystem.desafiobackvotos.vo.SessaoVotacaoVO;
import io.swagger.annotations.ApiOperation;

/** Classe que representa um controller para tratar informações de <code>SessaoVotacao</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@RestController
@RequestMapping("/sessaoVotacao")
public class SessaoVotacaoController {
	
	@Autowired
	private SessaoVotacaoServiceImpl sessaoVotacaoService;
	
	@ApiOperation("Salva uma nova sessao de votacao")
	@PostMapping("/abrir")
	public ResponseEntity<SessaoVotacaoVO> abrir(@RequestBody @Valid SessaoVotacaoDTO sessaoVotacaoDTO, UriComponentsBuilder uriBuilder) throws DesafioBackVotosException{
		SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrir(sessaoVotacaoDTO);
		URI uri = uriBuilder.path("/sessaoVotacao/{idSessaoVotacao}").buildAndExpand(sessaoVotacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new SessaoVotacaoVO(sessaoVotacao));
	}
	
	@ApiOperation("Busca uma sessao de votacao pelo id")
	@GetMapping("/{idSessaoVotacao}")
	public ResponseEntity<SessaoVotacaoVO> buscar(@PathVariable("idSessaoVotacao") Long idSessaoVotacao) throws DesafioBackVotosException{
		SessaoVotacao sessaoVotacao = sessaoVotacaoService.buscar(idSessaoVotacao);
		return ResponseEntity.ok(new SessaoVotacaoVO(sessaoVotacao));
	}
	
	@ApiOperation("Contabilizar os votos de uma sessao de votacao")
	@GetMapping("/contabilizar-votos/{idSessaoVotacao}")
	public ResponseEntity<ResultadoVotosVO> contabilizarVotos(@PathVariable("idSessaoVotacao") Long idSessaoVotacao) throws DesafioBackVotosException{
		ResultadoVotosVO resultado = sessaoVotacaoService.contabilizarVotos(idSessaoVotacao);
		return ResponseEntity.ok(resultado);
	}


}
