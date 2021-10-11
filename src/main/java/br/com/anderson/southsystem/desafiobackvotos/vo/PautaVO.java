package br.com.anderson.southsystem.desafiobackvotos.vo;

import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

public class PautaVO {
	
	private Long id;
	
	private String descricao;
	
	public PautaVO(Pauta pauta) {
		this.id = pauta.getId();
		this.descricao = pauta.getDescricao();
	}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
