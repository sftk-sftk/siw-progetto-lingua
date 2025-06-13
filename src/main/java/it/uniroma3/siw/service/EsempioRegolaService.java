package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.repository.EsempioRegolaRepository;

@Service
public class EsempioRegolaService {
	@Autowired 
	private EsempioRegolaRepository esempioRegolaRepository;
}
