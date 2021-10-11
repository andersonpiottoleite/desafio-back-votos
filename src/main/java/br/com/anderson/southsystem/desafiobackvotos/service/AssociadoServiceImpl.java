package br.com.anderson.southsystem.desafiobackvotos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.anderson.southsystem.desafiobackvotos.dto.AssociadoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;
import br.com.anderson.southsystem.desafiobackvotos.repository.AssociadoRepository;

/** Classe que implementa o contrato <code>AssociadoService</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@Service
public class AssociadoServiceImpl implements AssociadoService{
	
	@Autowired
	private AssociadoRepository associadoRepository;

	public Associado salvar(AssociadoDTO associadoDTO) {
		Associado Associado = new Associado(associadoDTO.getNome(), associadoDTO.getCpf());
		return associadoRepository.save(Associado);
	}
	
	public Associado buscar(Long idAssociado) throws DesafioBackVotosException {
		return associadoRepository.findById(idAssociado)
				.orElseThrow(() -> new DesafioBackVotosException("Associado não encontrato com o id " + idAssociado));
	}

}
