package br.com.anderson.southsystem.desafiobackvotos.dto;

import javax.validation.constraints.NotNull;

import br.com.anderson.southsystem.desafiobackvotos.enumeracao.OpcaoVotoEnum;

public class VotoDTO {

	@NotNull(message = "O id do Associado é obrigatório!")
	private Long idAssociado;
	
	@NotNull(message = "O id da Sessao Votacao é obrigatório!")
	private Long idSessaoVotacao;
	
	@NotNull(message = "A opção de Voto é obrigatória!")
	private OpcaoVotoEnum opcaoVoto;
	
	public VotoDTO(Long idAssociado, Long idSessaoVotacao, OpcaoVotoEnum opcaoVoto) {
		this.idAssociado = idAssociado;
		this.idSessaoVotacao = idSessaoVotacao;
		this.opcaoVoto = opcaoVoto;
	}
	public VotoDTO() {}

	public Long getIdAssociado() {
		return idAssociado;
	}

	public void setIdAssociado(Long idAssociado) {
		this.idAssociado = idAssociado;
	}

	public Long getIdSessaoVotacao() {
		return idSessaoVotacao;
	}

	public void setIdSessaoVotacao(Long idSessaoVotacao) {
		this.idSessaoVotacao = idSessaoVotacao;
	}

	public OpcaoVotoEnum getOpcaoVoto() {
		return opcaoVoto;
	}

	public void setOpcaoVoto(OpcaoVotoEnum opcaoVoto) {
		this.opcaoVoto = opcaoVoto;
	}

}
