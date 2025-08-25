package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.RegolaGrammaticale;
import it.uniroma3.siw.model.Testo;
import it.uniroma3.siw.repository.TestoRepository;

@Service
public class TestoService {
	@Autowired
	private TestoRepository testoRepository;

	public List<Testo> findAll() {
		List<Testo> lista = new ArrayList<>();
		Iterable<Testo> testi = this.testoRepository.findAll();
		for (Testo t : testi) {
			lista.add(t);
		}
		return lista;
	}

	public Testo findById(Long id) {
		return this.testoRepository.findById(id).orElse(null);
	}

}
