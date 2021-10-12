package br.com.anderson.southsystem.desafiobackvotos.service;

import static br.com.anderson.southsystem.desafiobackvotos.validations.VotoBusinessValidation.validaSeAssociadoJaVotou;
import static br.com.anderson.southsystem.desafiobackvotos.validations.VotoBusinessValidation.validaSessaoAberta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.anderson.southsystem.desafiobackvotos.dto.VotoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;
import br.com.anderson.southsystem.desafiobackvotos.repository.AssociadoRepository;
import br.com.anderson.southsystem.desafiobackvotos.repository.SessaoVotacaoRepository;
import br.com.anderson.southsystem.desafiobackvotos.repository.VotoRepository;
import br.com.anderson.southsystem.desafiobackvotos.validations.VotoBusinessValidation;

/** Classe que implementa o contrato <code>VotoService</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@Service
public class VotoServiceImpl implements VotoService {
	
	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private VotoBusinessValidation votoBusinessValidation;
	
	public VotoServiceImpl() {}
	
	/** Construtor para ser usado em mock de testes unitarios
	 * 
	 * @param sessaoVotacaoRepository
	 * @param associadoRepository
	 * @param votoBusinessValidation
	 */
	public VotoServiceImpl(SessaoVotacaoRepository sessaoVotacaoRepository, AssociadoRepository associadoRepository, 
			VotoBusinessValidation votoBusinessValidation, VotoRepository votoRepository) {
		this.sessaoVotacaoRepository = sessaoVotacaoRepository;
		this.associadoRepository = associadoRepository;
		this.votoBusinessValidation = votoBusinessValidation;
		this.votoRepository = votoRepository;
		
	}

	@Transactional
	public Voto salvar(VotoDTO votoDTO) throws DesafioBackVotosException {
		SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(votoDTO.getIdSessaoVotacao())
		.orElseThrow(() -> new DesafioBackVotosException("Sessao Votacao nao encontrada com o id " + votoDTO.getIdSessaoVotacao()));
		
		validaSessaoAberta(sessaoVotacao);
		
		Associado associado = associadoRepository.findById(votoDTO.getIdAssociado())
				.orElseThrow(() -> new DesafioBackVotosException("Associado nao encontrado com o id " + votoDTO.getIdAssociado()));
		
		validaSeAssociadoJaVotou(sessaoVotacao, associado);
		votoBusinessValidation.validaAssociadoPodeVotar(associado.getCpf());
		
		Voto voto = new Voto(votoDTO.getOpcaoVoto().isVotoAFavor(), associado, sessaoVotacao);
		sessaoVotacao.getVotos().add(voto);
		
		return votoRepository.save(voto);
	}

	public Voto buscar(Long idVoto) throws DesafioBackVotosException {
		return votoRepository.findById(idVoto)
		.orElseThrow(() -> new DesafioBackVotosException("Voto nao encontrado com o id " + idVoto));
	}
}
