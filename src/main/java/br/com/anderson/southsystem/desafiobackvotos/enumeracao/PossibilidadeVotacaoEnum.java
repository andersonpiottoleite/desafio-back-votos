package br.com.anderson.southsystem.desafiobackvotos.enumeracao;

public enum PossibilidadeVotacaoEnum {
	
	ABLE_TO_VOTE("ABLE_TO_VOTE"),
	
	UNABLE_TO_VOTE("UNABLE_TO_VOTE");
	
	private String value;
	
	private PossibilidadeVotacaoEnum (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
