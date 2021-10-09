package br.com.anderson.southsystem.desafiobackvotos.vo;

import br.com.anderson.southsystem.desafiobackvotos.model.Associado;

public class AssociadoVO {

	private Long id;
	
	private String nome;
	
	private String cpf;
	
	public AssociadoVO() {}

	public AssociadoVO(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cpf= associado.getCpf();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
}
