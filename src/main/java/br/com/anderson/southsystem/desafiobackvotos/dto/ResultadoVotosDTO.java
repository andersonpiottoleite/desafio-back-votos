package br.com.anderson.southsystem.desafiobackvotos.dto;

import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

public class ResultadoVotosDTO {

	private Long idPauta;

	private Long quantidadeTotalVotos;

	private Long quantidadeVotosAFavor;

	private Long quantidadeVotosContra;

	public ResultadoVotosDTO() {
	}

	public ResultadoVotosDTO(Pauta pauta) {
		this.idPauta = pauta.getId();
		this.quantidadeTotalVotos = pauta.getVotos().stream().count();
		this.quantidadeVotosAFavor = pauta.getVotos().stream().filter(v -> v.isVotoAFavor()).count();
		this.quantidadeVotosContra = pauta.getVotos().stream().filter(v -> !v.isVotoAFavor()).count();
	}

	public Long getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}

	public Long getQuantidadeTotalVotos() {
		return quantidadeTotalVotos;
	}

	public void setQuantidadeTotalVotos(Long quantidadeTotalVotos) {
		this.quantidadeTotalVotos = quantidadeTotalVotos;
	}

	public Long getQuantidadeVotosAFavor() {
		return quantidadeVotosAFavor;
	}

	public void setQuantidadeVotosAFavor(Long quantidadeVotosAFavor) {
		this.quantidadeVotosAFavor = quantidadeVotosAFavor;
	}

	public Long getQuantidadeVotosContra() {
		return quantidadeVotosContra;
	}

	public void setQuantidadeVotosContra(Long quantidadeVotosContra) {
		this.quantidadeVotosContra = quantidadeVotosContra;
	}

}
