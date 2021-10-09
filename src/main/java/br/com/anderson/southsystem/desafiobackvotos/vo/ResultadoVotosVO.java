package br.com.anderson.southsystem.desafiobackvotos.vo;

import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

public class ResultadoVotosVO {

	private Long idPauta;

	private Long quantidadeTotalVotos;

	private Long quantidadeVotosAFavor;

	private Long quantidadeVotosContra;

	public ResultadoVotosVO() {
	}

	public ResultadoVotosVO(Pauta pauta) {
		this.idPauta = pauta.getId();
		this.quantidadeTotalVotos = pauta.getVotos().stream().count();
		this.quantidadeVotosAFavor = pauta.getVotos().stream().filter(v -> v.isVotoAFavor()).count();
		this.quantidadeVotosContra = pauta.getVotos().stream().filter(v -> !v.isVotoAFavor()).count();
	}

	public Long getIdPauta() {
		return idPauta;
	}

	public Long getQuantidadeTotalVotos() {
		return quantidadeTotalVotos;
	}

	public Long getQuantidadeVotosAFavor() {
		return quantidadeVotosAFavor;
	}

	public Long getQuantidadeVotosContra() {
		return quantidadeVotosContra;
	}

}
