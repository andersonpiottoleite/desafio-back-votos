package br.com.anderson.southsystem.desafiobackvotos.validations;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.anderson.southsystem.desafiobackvotos.enumeracao.PossibilidadeVotacaoEnum;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;
import br.com.anderson.southsystem.desafiobackvotos.rest.InfoAssociadoRest;
import br.com.anderson.southsystem.desafiobackvotos.vo.AssociadoPossibilidadeVotacaoVO;

/** Classe de validação de regras de negócio do <code>Voto</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

@Component
public class VotoBusinessValidation {
	
	private static final Logger log = LoggerFactory.getLogger(VotoBusinessValidation.class);
	
	@Autowired
	private InfoAssociadoRest infoAssociadoRest;
	
	public VotoBusinessValidation() {}
	
	/** Construtor para ser usado em mock de testes unitarios
	 * 
	 * @param infoAssociadoRest
	 */
	public VotoBusinessValidation(InfoAssociadoRest infoAssociadoRest) {
		this.infoAssociadoRest = infoAssociadoRest;
	}
	
	public void validaAssociadoPodeVotar(String cpf) throws DesafioBackVotosException {
		AssociadoPossibilidadeVotacaoVO infoAssociadoPossibilidadeVotacao = infoAssociadoRest.getInfoAssociadoPossibilidadeVotacao(cpf);
		
		if(PossibilidadeVotacaoEnum.ABLE_TO_VOTE.getValue()
				.equals(infoAssociadoPossibilidadeVotacao.getStatus())) {
			
			log.info("Usuario do cpf {} habilitado para votar", cpf);
			
		} else if (PossibilidadeVotacaoEnum.UNABLE_TO_VOTE.getValue()
				.equals(infoAssociadoPossibilidadeVotacao.getStatus())) {
			
			log.info("O Associado de cpf "+ cpf +", não tem permissao para votar!");
			throw new DesafioBackVotosException("O Associado de cpf "+ cpf +", não tem permissao para votar!");
		}
	}
	
	public static void validaSessaoAberta(SessaoVotacao sessaoVotacao) throws DesafioBackVotosException {
		if (sessaoVotacao.isEncerrada()) {
			log.info("Sessão encerrada, não é possivel votar!");
			throw new DesafioBackVotosException("Sessão encerrada, não é possivel votar!");
		}
	}
	
	public static void validaSeAssociadoJaVotou(SessaoVotacao sessaoVotacao, Associado associado) throws DesafioBackVotosException {
		Optional<Voto> voto = sessaoVotacao.getVotos().stream()
		.filter(v -> v.getAssociado().getId().equals(associado.getId())).findAny();
		if(voto.isPresent()) {
			log.info("Sessão encerrada, não é possivel votar!");
			throw new DesafioBackVotosException("O associado de id " + associado.getId() + " já votou para essa pauta!");
		}
	}

}
