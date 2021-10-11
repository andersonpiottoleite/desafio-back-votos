package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.AssociadoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;

/** interface que representa um contrato das operacoes do servico de <code>Associado</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public interface AssociadoService {
	
	Associado salvar(AssociadoDTO associadoDTO);
	
	Associado buscar(Long idAssociado) throws DesafioBackVotosException;

}
