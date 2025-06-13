package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Vocabolo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String nome;
	private String traduzione;
	private String pinyin;
	
	@ManyToOne
	private ParteDelDiscorso parte_del_discorso;
	
	@ManyToMany
	private List<Vocabolo> e_classificatore_di;
	
	@ManyToMany(mappedBy="e_classificatore_di")
	private List<Vocabolo> e_classificato_da;

	@Override
	public int hashCode() {
		return Objects.hash(e_classificato_da, e_classificatore_di, id, nome, parte_del_discorso, pinyin, traduzione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vocabolo other = (Vocabolo) obj;
		return Objects.equals(e_classificato_da, other.e_classificato_da)
				&& Objects.equals(e_classificatore_di, other.e_classificatore_di) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(parte_del_discorso, other.parte_del_discorso)
				&& Objects.equals(pinyin, other.pinyin) && Objects.equals(traduzione, other.traduzione);
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

	public String getTraduzione() {
		return traduzione;
	}

	public void setTraduzione(String traduzione) {
		this.traduzione = traduzione;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public ParteDelDiscorso getParte_del_discorso() {
		return parte_del_discorso;
	}

	public void setParte_del_discorso(ParteDelDiscorso parte_del_discorso) {
		this.parte_del_discorso = parte_del_discorso;
	}

	public List<Vocabolo> getE_classificatore_di() {
		return e_classificatore_di;
	}

	public void setE_classificatore_di(List<Vocabolo> e_classificatore_di) {
		this.e_classificatore_di = e_classificatore_di;
	}

	public List<Vocabolo> getE_classificato_da() {
		return e_classificato_da;
	}

	public void setE_classificato_da(List<Vocabolo> e_classificato_da) {
		this.e_classificato_da = e_classificato_da;
	}
	
}
