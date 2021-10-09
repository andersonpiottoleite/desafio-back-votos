package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.AssociadoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;

public interface AssociadoService {
	
	Associado salvar(AssociadoDTO associadoDTO);
	
	Associado buscar(Long idAssociado) throws DesafioBackVotosException;

}
