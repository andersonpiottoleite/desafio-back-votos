package br.com.anderson.southsystem.desafiobackvotos.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private boolean votoAFavor;
	
	@ManyToOne
	private Associado associado;
	
	@ManyToOne
	private Pauta pauta;
	
	public Voto(boolean votoAFavor, Associado associado, Pauta pauta) {
		this.votoAFavor = votoAFavor;
		this.associado = associado;
		this.pauta = pauta;
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

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(associado, id, pauta, votoAFavor);
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
				&& Objects.equals(pauta, other.pauta) && votoAFavor == other.votoAFavor;
	}
}
