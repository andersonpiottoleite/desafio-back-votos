package br.com.anderson.southsystem.desafiobackvotos.enumeracao;

public enum OpcaoVotoEnum {
	
	SIM(true),
	
	NAO(false);
	
	private boolean votoAFavor;
	
	private OpcaoVotoEnum (boolean votoAFavor) {
		this.votoAFavor = votoAFavor;
	}
	
	public boolean isVotoAFavor() {
		return this.votoAFavor;
	}

}
