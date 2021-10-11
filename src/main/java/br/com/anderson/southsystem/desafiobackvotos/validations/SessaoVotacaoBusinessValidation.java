package br.com.anderson.southsystem.desafiobackvotos.validations;

import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

/** Classe de validação de regras de negócio da <code>SessaoVotacao</code>
 * 
 * @author Anderson Piotto
 * @since 09/10/2021
 * @version 1.0.0
 */

public class SessaoVotacaoBusinessValidation {
	
	private static final Logger log = LoggerFactory.getLogger(SessaoVotacaoBusinessValidation.class);
	
	public static void valida(SessaoVotacaoDTO sessaoVotacaoDTO) throws DesafioBackVotosException {
		if(Objects.nonNull(sessaoVotacaoDTO.getDataEncerramento())
				&& sessaoVotacaoDTO.getDataEncerramento().isBefore(LocalDateTime.now())) {
			log.info("A data de encerramento não pode ser anterior a data atual");
			throw new DesafioBackVotosException("A data de encerramento não pode ser anterior a data atual");
		}
	}
	
	public static void validaExistenciaVotos(SessaoVotacao sessaoVotacao) throws DesafioBackVotosException {
		if (sessaoVotacao.getVotos().isEmpty()) {
			log.info("Sessao sem votos para serem contabilizados");
			throw new DesafioBackVotosException("Sessao sem votos para serem contabilizados");
		}
	}

}
