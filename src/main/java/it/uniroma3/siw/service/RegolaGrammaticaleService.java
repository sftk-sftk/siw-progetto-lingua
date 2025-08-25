package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.RegolaGrammaticale;
import it.uniroma3.siw.repository.RegolaGrammaticaleRepository;

@Service
public class RegolaGrammaticaleService {
	@Autowired
	private RegolaGrammaticaleRepository regolaGrammaticaleRepository;

	public List<RegolaGrammaticale> findAll() {
		List<RegolaGrammaticale> lista = new ArrayList<>();
		Iterable<RegolaGrammaticale> regole = this.regolaGrammaticaleRepository.findAll();
		for (RegolaGrammaticale r : regole) {
			lista.add(r);
		}
		return lista;
	}

	public RegolaGrammaticale findById(Long id) {
		return this.regolaGrammaticaleRepository.findById(id).orElse(null);
	}
}
