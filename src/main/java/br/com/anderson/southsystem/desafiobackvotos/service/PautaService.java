package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

public interface PautaService {

	Pauta salvar(PautaDTO pautaDTO) throws DesafioBackVotosException;
	
	Pauta buscar(Long idPauta) throws DesafioBackVotosException;

}
