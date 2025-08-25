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
import it.uniroma3.siw.model.Testo;
import it.uniroma3.siw.service.TestoService;

@Controller
@RequestMapping("/lingua/cinese")
public class TestoController {
	@Autowired TestoService testoService;
	
	@GetMapping("/testi")
	public String getRegoleGrammaticali(Model model) {
		List<Testo> tuttiITesti = testoService.findAll();

		List<Testo> testiLivelloPrincipiante = tuttiITesti.stream()
				.filter(r -> r.getLivello() == LivelloLinguistico.PRINCIPIANTE).toList();

		List<Testo> testiLivelloIntermedio = tuttiITesti.stream()
				.filter(r -> r.getLivello() == LivelloLinguistico.INTERMEDIO).toList();

		List<Testo> testiLivelloAvanzato = tuttiITesti.stream()
				.filter(r -> r.getLivello() == LivelloLinguistico.AVANZATO).toList();

		model.addAttribute("testiLivelloPrincipiante", testiLivelloPrincipiante);
		model.addAttribute("testiLivelloIntermedio", testiLivelloIntermedio);
		model.addAttribute("testiLivelloAvanzato", testiLivelloAvanzato);

		return "/study/testi";
	}
	
	@GetMapping("/testo/{id}")
	public String getRegolaGrammaticale(@PathVariable("id") Long id,Model model) {
		Testo testo = testoService.findById(id);
		model.addAttribute("testo", testo);
		
		return "/study/testo";
	}

}
