package br.com.anderson.southsystem.desafiobackvotos.validations;

import br.com.anderson.southsystem.desafiobackvotos.exception.DesafioBackVotosException;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

public class PautaBusinesValidation {
	
	public static void validaExistenciaVotos(Pauta pauta) throws DesafioBackVotosException {
		if (pauta.getVotos().isEmpty()) {
			throw new DesafioBackVotosException("Pauta sem votos para serem contabilizados");
		}
	}

}
