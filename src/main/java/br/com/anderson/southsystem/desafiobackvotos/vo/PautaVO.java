package br.com.anderson.southsystem.desafiobackvotos.vo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.anderson.southsystem.desafiobackvotos.enumeracao.OpcaoVotoEnum;
import br.com.anderson.southsystem.desafiobackvotos.model.Pauta;

public class PautaVO {
	
	private Long id;
	
	private String descricao;
	
	private List<VotoVO> votos;
	
	public PautaVO(Pauta pauta) {
		this.id = pauta.getId();
		this.descricao = pauta.getDescricao();
		if(Objects.nonNull(pauta.getVotos()) && !pauta.getVotos().isEmpty()) {
			List<VotoVO> votosVO = pauta.getVotos().stream().map( v -> {
				VotoVO votoVo = new VotoVO();
				votoVo.setId(id);
				votoVo.setAssociado(new AssociadoVO(v.getAssociado()));
				votoVo.setSessaoVotacao(new SessaoVotacaoVO(v.getSessaoVotacao()));
				votoVo.setOpcaoVoto((v.isVotoAFavor() ? OpcaoVotoEnum.SIM : OpcaoVotoEnum.NAO));
				
				return votoVo;
			}).collect(Collectors.toList());
			
			this.votos = votosVO;
		}
	}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<VotoVO> getVotos() {
		return votos;
	}
	
	
}
