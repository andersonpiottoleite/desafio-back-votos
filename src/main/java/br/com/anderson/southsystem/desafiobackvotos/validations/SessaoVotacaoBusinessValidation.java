package br.com.anderson.southsystem.desafiobackvotos.validations;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.anderson.southsystem.desafiobackvotos.dto.SessaoVotacaoDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

public class SessaoVotacaoBusinessValidation {
	
	public static void valida(SessaoVotacaoDTO sessaoVotacaoDTO) throws DesafioBackVotosException {
		if(Objects.nonNull(sessaoVotacaoDTO.getDataEncerramento())
				&& sessaoVotacaoDTO.getDataEncerramento().isBefore(LocalDateTime.now())) {
			throw new DesafioBackVotosException("A data de encerramento não pode ser anterior a data atual");
		}
	}
	
	public static void validaExistenciaVotos(SessaoVotacao sessaoVotacao) throws DesafioBackVotosException {
		if (sessaoVotacao.getVotos().isEmpty()) {
			throw new DesafioBackVotosException("Sessao sem votos para serem contabilizados");
		}
	}

}
