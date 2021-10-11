package br.com.anderson.southsystem.desafiobackvotos.vo;

import br.com.anderson.southsystem.desafiobackvotos.enumeracao.OpcaoVotoEnum;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;

public class VotoVO {
	
	private Long id;

	private AssociadoVO associado;
	
	private OpcaoVotoEnum opcaoVoto;
	
	public VotoVO(Voto voto) {
		this.id = voto.getId();
		this.associado = new AssociadoVO(voto.getAssociado());
		this.opcaoVoto = (voto.isVotoAFavor() ? OpcaoVotoEnum.SIM : OpcaoVotoEnum.NAO);
	}
	
	public VotoVO() {}


	public Long getId() {
		return id;
	}

	public AssociadoVO getAssociado() {
		return associado;
	}

	public OpcaoVotoEnum getOpcaoVoto() {
		return opcaoVoto;
	}

}
