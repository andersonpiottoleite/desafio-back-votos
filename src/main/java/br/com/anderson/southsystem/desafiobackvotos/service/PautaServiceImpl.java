package br.com.anderson.southsystem.desafiobackvotos.service;

import static br.com.anderson.southsystem.desafiobackvotos.validations.PautaBusinesValidation.validaExistenciaVotos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.repository.PautaRepository;
import br.com.anderson.southsystem.desafiobackvotos.vo.ResultadoVotosVO;

@Service
public class PautaServiceImpl implements PautaService {
	
	@Autowired
	private PautaRepository pautaRepository;

	public Pauta salvar(PautaDTO pautaDTO) throws DesafioBackVotosException {
		return pautaRepository.save(new Pauta(pautaDTO.getDescricao()));
	}

	public ResultadoVotosVO contabilizarVotos(Long idPauta) throws DesafioBackVotosException {
		Pauta pauta = buscaPautaPorId(idPauta);
		validaExistenciaVotos(pauta);
		return new ResultadoVotosVO(pauta);
	}

	public Pauta buscar(Long idPauta) throws DesafioBackVotosException {
		return buscaPautaPorId(idPauta);
	}
	
	private Pauta buscaPautaPorId(Long idPauta) throws DesafioBackVotosException {
		return pautaRepository.findById(idPauta)
				.orElseThrow(() -> new DesafioBackVotosException("Pauta não encontrada com o id " + idPauta));
	}
}
