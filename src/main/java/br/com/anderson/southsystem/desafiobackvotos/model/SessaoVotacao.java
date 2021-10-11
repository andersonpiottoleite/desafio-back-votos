package br.com.anderson.southsystem.desafiobackvotos.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SessaoVotacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * dataEncerramento default - 1 minuto
	 */
	private LocalDateTime dataEncerramento = LocalDateTime.now().plusMinutes(1);
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Pauta pauta;
	
	private boolean encerrada = false;

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

	@Override
	public int hashCode() {
		return Objects.hash(dataEncerramento, id, pauta);
	}
	
	public boolean isEncerrada() {
		return encerrada;
	}

	public void encerra() {
		this.encerrada = true;
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
		return Objects.equals(dataEncerramento, other.dataEncerramento) && Objects.equals(id, other.id)
				&& Objects.equals(pauta, other.pauta);
	}
}
