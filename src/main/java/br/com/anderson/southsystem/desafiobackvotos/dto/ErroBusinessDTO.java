package br.com.anderson.southsystem.desafiobackvotos.dto;

public class ErroBusinessDTO {
	
	private String erro;

	public ErroBusinessDTO(String erro) {
		this.erro = erro;
	}

	public ErroBusinessDTO() {
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
