package br.com.anderson.southsystem.desafiobackvotos.validations;

import org.apache.logging.log4j.util.Strings;

import br.com.anderson.southsystem.desafiobackvotos.dto.PautaDTO;
import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

public class PautaValidation {
	
	public static void valida(PautaDTO pautaDTO) throws DesafioBackVotosException {
		if(Strings.isEmpty(pautaDTO.getDescricao())) {
			throw new DesafioBackVotosException("A descrição da Pauta não pode ser nula ou vazia");
		}
	}
	
	public static void validaExistenciaVotos(Pauta pauta) throws DesafioBackVotosException {
		if (pauta.getVotos().isEmpty()) {
			throw new DesafioBackVotosException("Pauta sem votos para serem contabilizados");
		}
	}

}
