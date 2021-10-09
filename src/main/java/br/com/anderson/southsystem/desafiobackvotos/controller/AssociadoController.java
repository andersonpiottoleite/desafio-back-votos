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

import br.com.anderson.southsystem.desafiobackvotos.dto.AssociadoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;
import br.com.anderson.southsystem.desafiobackvotos.service.AssociadoServiceImpl;
import br.com.anderson.southsystem.desafiobackvotos.vo.AssociadoVO;
import io.swagger.annotations.ApiOperation;

/** Classe que representa um controller para tratar informações de <code>Associado</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@RestController
@RequestMapping("/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoServiceImpl associadoService;
	
	@ApiOperation("Salva um novo associado")
	@PostMapping("/salvar")
	public ResponseEntity<AssociadoVO> salvar(@RequestBody @Valid AssociadoDTO associadoDTO, UriComponentsBuilder uriBuilder) throws DesafioBackVotosException{
		Associado associado = associadoService.salvar(associadoDTO);
		URI uri = uriBuilder.path("/associado/{idAssociado}").buildAndExpand(associado.getId()).toUri();
        return ResponseEntity.created(uri).body(new AssociadoVO(associado));
	}
	
	@ApiOperation("Busca um associado pelo id")
	@GetMapping("/{idAssociado}")
	public ResponseEntity<AssociadoVO> buscar(@PathVariable("idAssociado") Long idAssociado) throws DesafioBackVotosException{
		Associado associado = associadoService.buscar(idAssociado);
		return ResponseEntity.ok(new AssociadoVO(associado));
	}

}
