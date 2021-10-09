package br.com.anderson.southsystem.desafiobackvotos.dto;

public class AssociadoDTO {
	
	private String nome;
	
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
