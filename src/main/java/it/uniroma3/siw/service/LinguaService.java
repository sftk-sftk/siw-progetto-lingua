package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Lingua;
import it.uniroma3.siw.repository.LinguaRepository;

@Service
public class LinguaService {
	@Autowired
	private LinguaRepository linguaRepository;
	
	public Lingua getLinguaById() {
		Long o=(long) 0;
		return this.linguaRepository.findById(o).get();
	}
}
