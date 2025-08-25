package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.Enum.LivelloLinguistico;
import it.uniroma3.siw.model.RegolaGrammaticale;
import it.uniroma3.siw.service.RegolaGrammaticaleService;

@Controller
@RequestMapping("/lingua/cinese")
public class RegolaGrammaticaleController {
	@Autowired
	RegolaGrammaticaleService regolaGrammaticaleService;

	@GetMapping("/regoleGrammaticali")
	public String getRegoleGrammaticali(Model model) {
		List<RegolaGrammaticale> tutteLeRegole = regolaGrammaticaleService.findAll();

		List<RegolaGrammaticale> regoleLivelloPrincipiante = tutteLeRegole.stream()
				.filter(r -> r.getLivello() == LivelloLinguistico.PRINCIPIANTE).toList();

		List<RegolaGrammaticale> regoleLivelloIntermedio = tutteLeRegole.stream()
				.filter(r -> r.getLivello() == LivelloLinguistico.INTERMEDIO).toList();

		List<RegolaGrammaticale> regoleLivelloAvanzato = tutteLeRegole.stream()
				.filter(r -> r.getLivello() == LivelloLinguistico.AVANZATO).toList();

		model.addAttribute("regoleLivelloPrincipiante", regoleLivelloPrincipiante);
		model.addAttribute("regoleLivelloIntermedio", regoleLivelloIntermedio);
		model.addAttribute("regoleLivelloAvanzato", regoleLivelloAvanzato);

		return "/study/regoleGrammaticali";
	}
	
	@GetMapping("/regolaGrammaticale/{id}")
	public String getRegolaGrammaticale(@PathVariable("id") Long id,Model model) {
		RegolaGrammaticale regola = regolaGrammaticaleService.findById(id);
		model.addAttribute("regola", regola);
		
		return "/study/regolaGrammaticale";
	}

}
