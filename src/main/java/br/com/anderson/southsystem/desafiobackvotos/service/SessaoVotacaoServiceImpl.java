package br.com.anderson.southsystem.desafiobackvotos.service;

import static br.com.anderson.southsystem.desafiobackvotos.validations.SessaoVotacaoValidation.valida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.repository.PautaRepository;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService{
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;

	public SessaoVotacao abrir(SessaoVotacaoDTO sessaoVotacaoDTO) throws DesafioBackVotosException {
		Pauta pauta = pautaRepository.findById(sessaoVotacaoDTO.getIdPauta()).orElseThrow(() -> new DesafioBackVotosException("Pauta não encontrada com o id " + sessaoVotacaoDTO.getIdPauta()));
		valida(sessaoVotacaoDTO);
		return sessaoVotacaoRepository.save(new SessaoVotacao(pauta, sessaoVotacaoDTO.getDataEncerramento()));
	}

	public SessaoVotacao buscar(Long idSessaoVotacao) throws DesafioBackVotosException {
		return sessaoVotacaoRepository.findById(idSessaoVotacao).orElseThrow(() -> new DesafioBackVotosException("SessaoVotacao não encontrada com o id " + idSessaoVotacao));
	}

}
