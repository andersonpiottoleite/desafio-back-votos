package br.com.anderson.southsystem.desafiobackvotos.dto;

import javax.validation.constraints.NotEmpty;

public class PautaDTO {

	@NotEmpty(message = "A descricao da pauta é obrigatória!")
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
