package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import it.uniroma3.siw.Enum.LivelloLinguistico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Testo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String titolo;
	private String testo;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING) // per salvare "A1", "A2", ecc. nel DB
	private LivelloLinguistico livello;
	
	@ManyToMany(mappedBy="testi")
	private List<RegolaGrammaticale> regole_grammaticali;

	@Override
	public int hashCode() {
		return Objects.hash(id, livello, regole_grammaticali, testo, titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Testo other = (Testo) obj;
		return Objects.equals(id, other.id) && livello == other.livello
				&& Objects.equals(regole_grammaticali, other.regole_grammaticali) && Objects.equals(testo, other.testo)
				&& Objects.equals(titolo, other.titolo);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public List<RegolaGrammaticale> getRegole_grammaticali() {
		return regole_grammaticali;
	}

	public void setRegole_grammaticali(List<RegolaGrammaticale> regole_grammaticali) {
		this.regole_grammaticali = regole_grammaticali;
	}

	public LivelloLinguistico getLivello() {
		return livello;
	}

	public void setLivello(LivelloLinguistico livello) {
		this.livello = livello;
	}
	
	
}
