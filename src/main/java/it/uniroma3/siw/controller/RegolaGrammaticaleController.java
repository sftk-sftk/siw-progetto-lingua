package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.service.RegolaGrammaticaleService;

@Controller
public class RegolaGrammaticaleController {
	@Autowired RegolaGrammaticaleService regolaGrammaticaleService;

}
