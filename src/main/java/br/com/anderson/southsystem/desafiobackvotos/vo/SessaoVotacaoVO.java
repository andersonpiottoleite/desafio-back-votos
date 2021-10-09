package br.com.anderson.southsystem.desafiobackvotos.vo;

import java.time.LocalDateTime;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;

public class SessaoVotacaoVO {

	private Long id;
	
	private LocalDateTime dataEncerramento;
	
	private PautaVO pauta;
	
	private boolean encerrada = false;

	public SessaoVotacaoVO(SessaoVotacao sessaoVotacao) {
		this.id = sessaoVotacao.getId();
		this.dataEncerramento = sessaoVotacao.getDataEncerramento();
		this.encerrada = sessaoVotacao.isEncerrada();
		this.pauta = new PautaVO(sessaoVotacao.getPauta());
	}

	public Long getId() {
		return id;
	}
	
	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	public PautaVO getPauta() {
		return pauta;
	}

	public boolean isEncerrada() {
		return encerrada;
	}
}
