package br.com.anderson.southsystem.desafiobackvotos.dto;

public class PautaDTO {

	private String descricao;

	public PautaDTO() {
	}

	public PautaDTO(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
