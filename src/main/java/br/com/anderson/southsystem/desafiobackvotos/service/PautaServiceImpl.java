package br.com.anderson.southsystem.desafiobackvotos.service;

import static br.com.anderson.southsystem.desafiobackvotos.validations.PautaValidation.valida;
import static br.com.anderson.southsystem.desafiobackvotos.validations.PautaValidation.validaExistenciaVotos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.dto.ResultadoVotosDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.repository.PautaRepository;

@Service
public class PautaServiceImpl implements PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;

	public Pauta salvar(PautaDTO pautaDTO) throws DesafioBackVotosException {
		valida(pautaDTO);
		return pautaRepository.save(new Pauta(pautaDTO.getDescricao()));
	}

	public ResultadoVotosDTO contabilizarVotos(Long idPauta) throws DesafioBackVotosException {
		Pauta pauta = pautaRepository.findById(idPauta)
				.orElseThrow(() -> new DesafioBackVotosException("Pauta não encontrada com o id " + idPauta));
		validaExistenciaVotos(pauta);
		return new ResultadoVotosDTO(pauta);
	}

	public Pauta buscar(Long idPauta) throws DesafioBackVotosException {
		return pautaRepository.findById(idPauta)
				.orElseThrow(() -> new DesafioBackVotosException("Pauta não encontrada com o id " + idPauta));
	}
}
