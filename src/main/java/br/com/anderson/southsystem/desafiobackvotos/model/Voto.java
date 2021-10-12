package br.com.anderson.southsystem.desafiobackvotos.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Voto {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;
	
	private boolean votoAFavor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Associado associado;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private SessaoVotacao sessaoVotacao;
	
	public Voto(boolean votoAFavor, Associado associado,SessaoVotacao sessaoVotacao) {
		this.votoAFavor = votoAFavor;
		this.associado = associado;
		this.sessaoVotacao = sessaoVotacao;
	}

	public Long getId() {
		return id;
	}

	public Voto() {}

	public boolean isVotoAFavor() {
		return votoAFavor;
	}

	public void setVotoAFavor(boolean votoAFavor) {
		this.votoAFavor = votoAFavor;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public SessaoVotacao getSessaoVotacao() {
		return sessaoVotacao;
	}

	public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
		this.sessaoVotacao = sessaoVotacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(associado, id, sessaoVotacao, votoAFavor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		return Objects.equals(associado, other.associado) && Objects.equals(id, other.id)
				&& Objects.equals(sessaoVotacao, other.sessaoVotacao) && votoAFavor == other.votoAFavor;
	}
}
