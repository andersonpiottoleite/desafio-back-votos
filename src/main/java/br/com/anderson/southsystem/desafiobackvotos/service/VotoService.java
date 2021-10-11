package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.VotoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;

public interface VotoService {
	
	Voto salvar(VotoDTO votoDTO) throws DesafioBackVotosException;
	
	Voto buscar(Long idVoto) throws DesafioBackVotosException;

}
