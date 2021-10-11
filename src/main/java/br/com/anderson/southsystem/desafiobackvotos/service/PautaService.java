package br.com.anderson.southsystem.desafiobackvotos.service;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

/** interface que representa um contrato das operacoes do servico de <code>Pauta</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public interface PautaService {

	Pauta salvar(PautaDTO pautaDTO) throws DesafioBackVotosException;
	
	Pauta buscar(Long idPauta) throws DesafioBackVotosException;

}
