package br.com.anderson.southsystem.desafiobackvotos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.repository.PautaRepository;

/** Classe que implementa o contrato <code>PautaService</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@Service
public class PautaServiceImpl implements PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;

	public Pauta salvar(PautaDTO pautaDTO) throws DesafioBackVotosException {
		return pautaRepository.save(new Pauta(pautaDTO.getDescricao()));
	}

	public Pauta buscar(Long idPauta) throws DesafioBackVotosException {
		return pautaRepository.findById(idPauta)
				.orElseThrow(() -> new DesafioBackVotosException("Pauta não encontrada com o id " + idPauta));
	}
}
