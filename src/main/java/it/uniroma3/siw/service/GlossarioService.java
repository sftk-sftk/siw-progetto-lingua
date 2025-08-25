package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Glossario;
import it.uniroma3.siw.model.Vocabolo;
import it.uniroma3.siw.repository.GlossarioRepository;

@Service
public class GlossarioService {
	@Autowired
	private GlossarioRepository glossarioRepository;
	
	public Glossario getGlossarioById() {
		Long o=(long) 0;
		return this.glossarioRepository.findById(o).get();
	}
	public List<Vocabolo> getVocaboli() {
		List<Vocabolo> vocaboli = new ArrayList<>();
		Glossario g= this.getGlossarioById();
		for (Vocabolo v: g.getVocaboli()) {
			vocaboli.add(v);
		}
		return vocaboli;
	}


}
