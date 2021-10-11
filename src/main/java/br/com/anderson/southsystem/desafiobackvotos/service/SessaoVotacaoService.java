package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

/** interface que representa um contrato das operacoes do servico de <code>SessaoVotacao</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public interface SessaoVotacaoService {
	
	SessaoVotacao abrir(SessaoVotacaoDTO sessaoVotacaoDTO) throws DesafioBackVotosException;
	
	SessaoVotacao buscar(Long idSessaoVotacao) throws DesafioBackVotosException;

}
