package br.com.anderson.southsystem.desafiobackvotos.dto;

public class ErroValidacaoCamposDTO {

	private String campo;

	private String erro;

	public ErroValidacaoCamposDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public ErroValidacaoCamposDTO(String erro) {
		this.erro = erro;
	}

	public ErroValidacaoCamposDTO() {
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
