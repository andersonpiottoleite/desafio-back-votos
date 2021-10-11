package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.VotoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;

/** interface que representa um contrato das operacoes do servico de <code>Voto</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public interface VotoService {
	
	Voto salvar(VotoDTO votoDTO) throws DesafioBackVotosException;
	
	Voto buscar(Long idVoto) throws DesafioBackVotosException;

}
