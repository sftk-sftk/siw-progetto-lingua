package it.uniroma3.siw.model;

import java.awt.Image;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Lingua {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String nome;
	@Column(columnDefinition = "TEXT")
    private String descrizione;
	//private Image image;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<LivelloLinguistico> livelli;
	
	@OneToOne
	private Glossario glossario;

	@Override
	public int hashCode() {
		return Objects.hash(descrizione, glossario, id, livelli, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lingua other = (Lingua) obj;
		return Objects.equals(descrizione, other.descrizione) && Objects.equals(glossario, other.glossario)
				&& Objects.equals(id, other.id) && Objects.equals(livelli, other.livelli)
				&& Objects.equals(nome, other.nome);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<LivelloLinguistico> getLivelli() {
		return livelli;
	}

	public void setLivelli(List<LivelloLinguistico> livelli) {
		this.livelli = livelli;
	}

	public Glossario getGlossario() {
		return glossario;
	}

	public void setGlossario(Glossario glossario) {
		this.glossario = glossario;
	}
	
}
