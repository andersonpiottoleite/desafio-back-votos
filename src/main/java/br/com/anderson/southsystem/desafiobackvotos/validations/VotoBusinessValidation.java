package br.com.anderson.southsystem.desafiobackvotos.validations;

import static br.com.anderson.southsystem.desafiobackvotos.rest.InfoAssociadoRest.getInfoAssociadoPossibilidadeVotacao;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.anderson.southsystem.desafiobackvotos.enumeracao.PossibilidadeVotacaoEnum;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Associado;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;
import br.com.anderson.southsystem.desafiobackvotos.vo.AssociadoPossibilidadeVotacaoVO;

public class VotoBusinessValidation {
	
	private static final Logger log = LoggerFactory.getLogger(VotoBusinessValidation.class);
	
	public static void validaAssociadoPodeVotar(String cpf) throws DesafioBackVotosException {
		AssociadoPossibilidadeVotacaoVO infoAssociadoPossibilidadeVotacao = getInfoAssociadoPossibilidadeVotacao(cpf);
		
		if(PossibilidadeVotacaoEnum.ABLE_TO_VOTE.getValue()
				.equals(infoAssociadoPossibilidadeVotacao.getStatus())) {
			
			log.info("Usuario do cpf {} habilitado para votar", cpf);
			
		} else if (PossibilidadeVotacaoEnum.UNABLE_TO_VOTE.getValue()
				.equals(infoAssociadoPossibilidadeVotacao.getStatus())) {
			
			throw new DesafioBackVotosException("O Associado de cpf "+ cpf +", não esta habilitado para votar!");
		}
	}
	
	public static void validaSessaoAberta(SessaoVotacao sessaoVotacao) throws DesafioBackVotosException {
		if (sessaoVotacao.isEncerrada()) {
			throw new DesafioBackVotosException("Sessão encerrada, não é possivel votar!");
		}
	}
	
	public static void validaSeAssociadoJaVotou(SessaoVotacao sessaoVotacao, Associado associado) throws DesafioBackVotosException {
		Optional<Voto> voto = sessaoVotacao.getVotos().stream()
		.filter(v -> v.getAssociado().getId().equals(associado.getId())).findAny();
		if(voto.isPresent()) {
			throw new DesafioBackVotosException("O associado de id " + associado.getId() + " já votou para essa pauta!");
		}
	}

}
