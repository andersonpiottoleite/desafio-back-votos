package br.com.anderson.southsystem.desafiobackvotos.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class SessaoVotacaoDTO {

	private LocalDateTime dataEncerramento;
	
	@NotNull(message = "O id da Pauta é obrigatório!")
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
}
