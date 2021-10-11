package br.com.anderson.southsystem.desafiobackvotos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Voto> votos;
	
	public Pauta(String descricao) {
		this.setDescricao(descricao);
	}
	
	public Pauta() {}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Voto> getVotos() {
		if(Objects.isNull(this.votos)) {
			votos = new ArrayList<Voto>();
		}
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, votos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pauta other = (Pauta) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(id, other.id)
				&& Objects.equals(votos, other.votos);
	}
}
