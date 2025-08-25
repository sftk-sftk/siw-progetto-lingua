package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import it.uniroma3.siw.Enum.LivelloLinguistico;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class RegolaGrammaticale {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String descrizioneBreve;
	private String descrizione;
	
	@OneToMany(cascade= {CascadeType.ALL})
	private List<EsempioRegola> esempi;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING) // per salvare "A1", "A2", ecc. nel DB
	private LivelloLinguistico livello;
	
	@ManyToMany
	private List<Testo> testi;

	@Override
	public int hashCode() {
		return Objects.hash(descrizione, descrizioneBreve, esempi, id, livello, nome, testi);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegolaGrammaticale other = (RegolaGrammaticale) obj;
		return Objects.equals(descrizione, other.descrizione)
				&& Objects.equals(descrizioneBreve, other.descrizioneBreve) && Objects.equals(esempi, other.esempi)
				&& Objects.equals(id, other.id) && livello == other.livello && Objects.equals(nome, other.nome)
				&& Objects.equals(testi, other.testi);
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

	public List<EsempioRegola> getEsempi() {
		return esempi;
	}

	public void setEsempi(List<EsempioRegola> esempi) {
		this.esempi = esempi;
	}

	public List<Testo> getTesti() {
		return testi;
	}

	public void setTesti(List<Testo> testi) {
		this.testi = testi;
	}

	public LivelloLinguistico getLivello() {
		return livello;
	}

	public void setLivello(LivelloLinguistico livello) {
		this.livello = livello;
	}

	public String getDescrizioneBreve() {
		return descrizioneBreve;
	}

	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}

}
