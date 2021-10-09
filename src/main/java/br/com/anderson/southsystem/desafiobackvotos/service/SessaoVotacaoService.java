package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

public interface SessaoVotacaoService {
	
	SessaoVotacao abrir(SessaoVotacaoDTO sessaoVotacaoDTO) throws DesafioBackVotosException;
	
	SessaoVotacao buscar(Long idSessaoVotacao) throws DesafioBackVotosException;

}
