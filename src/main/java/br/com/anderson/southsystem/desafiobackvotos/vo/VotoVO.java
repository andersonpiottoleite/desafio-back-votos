package br.com.anderson.southsystem.desafiobackvotos.vo;

import br.com.anderson.southsystem.desafiobackvotos.enumeracao.OpcaoVotoEnum;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;

public class VotoVO {
	
	private Long id;

	private AssociadoVO associado;
	
	private SessaoVotacaoVO sessaoVotacao;
	
	private OpcaoVotoEnum opcaoVoto;
	
	public VotoVO(Voto voto) {
		this.id = voto.getId();
		this.associado = new AssociadoVO(voto.getAssociado());
		this.sessaoVotacao = new SessaoVotacaoVO(voto.getSessaoVotacao());
		this.opcaoVoto = (voto.isVotoAFavor() ? OpcaoVotoEnum.SIM : OpcaoVotoEnum.NAO);
	}
	
	public VotoVO() {}


	public Long getId() {
		return id;
	}

	public AssociadoVO getAssociado() {
		return associado;
	}

	public SessaoVotacaoVO getSessaoVotacao() {
		return sessaoVotacao;
	}

	public OpcaoVotoEnum getOpcaoVoto() {
		return opcaoVoto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}

	public void setSessaoVotacao(SessaoVotacaoVO sessaoVotacao) {
		this.sessaoVotacao = sessaoVotacao;
	}

	public void setOpcaoVoto(OpcaoVotoEnum opcaoVoto) {
		this.opcaoVoto = opcaoVoto;
	}

}
