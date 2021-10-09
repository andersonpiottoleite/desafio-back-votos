package br.com.anderson.southsystem.desafiobackvotos.dto;

import javax.validation.constraints.NotEmpty;

public class AssociadoDTO {
	
	@NotEmpty(message = "O nome do associado é obrigatório!")
	private String nome;
	
	@NotEmpty(message = "O cpf do associado é obrigatório!")
	private String cpf;

	public AssociadoDTO(String nome, String cpf) {
		this.setNome(nome);
		this.setCpf(cpf);
	}
	
	public AssociadoDTO() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
