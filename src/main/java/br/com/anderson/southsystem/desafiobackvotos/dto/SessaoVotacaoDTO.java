package br.com.anderson.southsystem.desafiobackvotos.dto;

import java.time.LocalDateTime;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

public class SessaoVotacaoDTO {

	private LocalDateTime dataEncerramento;
	
	private Long idPauta;

	public SessaoVotacaoDTO(Long idPauta, LocalDateTime dataEncerramento) {
		this.idPauta = idPauta;
		this.dataEncerramento = dataEncerramento;
	}

	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Long getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}

	public SessaoVotacao convertoToSessaoVotacao() {
		// TODO Auto-generated method stub
		return null;
	}
}
