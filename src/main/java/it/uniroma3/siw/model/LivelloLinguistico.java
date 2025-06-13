package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class LivelloLinguistico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String livello;
	private String descrizione;

	@OneToMany
	private List<RegolaGrammaticale> regole;
	@OneToMany
	private List<Vocabolo> testi;
	@Override
	public int hashCode() {
		return Objects.hash(descrizione, id, livello, regole, testi);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivelloLinguistico other = (LivelloLinguistico) obj;
		return Objects.equals(descrizione, other.descrizione) && Objects.equals(id, other.id)
				&& Objects.equals(livello, other.livello) && Objects.equals(regole, other.regole)
				&& Objects.equals(testi, other.testi);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLivello() {
		return livello;
	}
	public void setLivello(String livello) {
		this.livello = livello;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<RegolaGrammaticale> getRegole() {
		return regole;
	}
	public void setRegole(List<RegolaGrammaticale> regole) {
		this.regole = regole;
	}
	public List<Vocabolo> getTesti() {
		return testi;
	}
	public void setTesti(List<Vocabolo> testi) {
		this.testi = testi;
	}

}
