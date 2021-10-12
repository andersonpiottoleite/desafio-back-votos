package br.com.anderson.southsystem.desafiobackvotos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class SessaoVotacao {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;
	
	/**
	 * dataEncerramento default - 1 minuto
	 */
	private LocalDateTime dataEncerramento = LocalDateTime.now().plusMinutes(1);
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pauta pauta;
	
	private boolean encerrada = false;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Voto> votos;

	public SessaoVotacao() {}

	public SessaoVotacao(Pauta pauta, LocalDateTime dataEncerramento) {
		this.pauta = pauta;
		if(Objects.nonNull(dataEncerramento)) {			
			this.dataEncerramento = dataEncerramento;
		}
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDateTime dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}
	
	public boolean isEncerrada() {
		return encerrada;
	}

	public void encerra() {
		this.encerrada = true;
	}

	public List<Voto> getVotos() {
		if(Objects.isNull(votos)) {
			votos = new ArrayList<Voto>();
		}
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataEncerramento, encerrada, id, pauta, votos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessaoVotacao other = (SessaoVotacao) obj;
		return Objects.equals(dataEncerramento, other.dataEncerramento) && encerrada == other.encerrada
				&& Objects.equals(id, other.id) && Objects.equals(pauta, other.pauta)
				&& Objects.equals(votos, other.votos);
	}
	
}
