package br.com.anderson.southsystem.desafiobackvotos.vo;

public class AssociadoPossibilidadeVotacaoVO {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "{'status':" + this.status +"}";
	}

}
