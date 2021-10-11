package br.com.anderson.southsystem.desafiobackvotos.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.anderson.southsystem.desafiobackvotos.model.SessaoVotacao;
import br.com.anderson.southsystem.desafiobackvotos.model.Voto;

public class SessaoVotacaoVO {

	private Long idSessao;
	
	private LocalDateTime dataEncerramento;
	
	private PautaVO pauta;
	
	private boolean encerrada = false;
	
	private List<VotoVO> votos;

	public SessaoVotacaoVO(SessaoVotacao sessaoVotacao) {
		this.idSessao = sessaoVotacao.getId();
		this.dataEncerramento = sessaoVotacao.getDataEncerramento();
		this.encerrada = sessaoVotacao.isEncerrada();
		this.pauta = new PautaVO(sessaoVotacao.getPauta());
		
		if(Objects.nonNull(sessaoVotacao.getVotos()) && !sessaoVotacao.getVotos().isEmpty()) {
			List<VotoVO> votosVO = sessaoVotacao.getVotos().stream().map( v -> {
				VotoVO votoVo = new VotoVO(v);
				return votoVo;
			}).collect(Collectors.toList());
			
			this.votos = votosVO;
		}
	}

	public SessaoVotacaoVO(Voto voto) {
		this.idSessao = voto.getSessaoVotacao().getId();
		this.dataEncerramento = voto.getSessaoVotacao().getDataEncerramento();
		this.encerrada = voto.getSessaoVotacao().isEncerrada();
		this.pauta = new PautaVO(voto.getSessaoVotacao().getPauta());
		
		if(Objects.nonNull(voto.getSessaoVotacao().getVotos()) && !voto.getSessaoVotacao().getVotos().isEmpty()) {
			List<VotoVO> votosVO = voto.getSessaoVotacao().getVotos().stream().map( v -> {
				VotoVO votoVo = new VotoVO(v);
				return votoVo;
			}).collect(Collectors.toList());
			
			this.votos = votosVO;
		}
	}

	public Long getIdSessao() {
		return idSessao;
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
	
	public List<VotoVO> getVotos() {
		return votos;
	}
}
