package br.com.anderson.southsystem.desafiobackvotos.vo;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

public class ResultadoVotosVO {

	private Long idSessao;
	
	private boolean encerrada;
	
	private PautaVO pauta;

	private Long quantidadeTotalVotos;

	private Long quantidadeVotosAFavor;

	private Long quantidadeVotosContra;

	public ResultadoVotosVO() {
	}

	public ResultadoVotosVO(SessaoVotacao sessaoVotacao) {
		this.idSessao = sessaoVotacao.getId();
		this.encerrada = sessaoVotacao.isEncerrada();
		this.pauta = new PautaVO(sessaoVotacao.getPauta());
		this.quantidadeTotalVotos = sessaoVotacao.getVotos().stream().count();
		this.quantidadeVotosAFavor = sessaoVotacao.getVotos().stream().filter(v -> v.isVotoAFavor()).count();
		this.quantidadeVotosContra = sessaoVotacao.getVotos().stream().filter(v -> !v.isVotoAFavor()).count();
	}

	public Long getIdSessao() {
		return idSessao;
	}
	
	public boolean isEncerrada() {
		return encerrada;
	}
	
	public PautaVO getPauta() {
		return pauta;
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
