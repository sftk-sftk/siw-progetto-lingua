package it.uniroma3.siw.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Vocabolo;
import it.uniroma3.siw.service.GlossarioService;

@Controller
public class GlossarioController {
	@Autowired GlossarioService glossarioService;

	@GetMapping("/lingua/cinese/glossario")
	public String getGlossario(Model model) {
		model.addAttribute("glossario", this.glossarioService.getGlossarioById());
		List<Vocabolo> vocaboli=this.glossarioService.getVocaboli();
		vocaboli.sort(Comparator.comparing(Vocabolo::getPinyin));
		model.addAttribute("vocaboli", vocaboli);
		
		return "glossario.html";
	}

}
