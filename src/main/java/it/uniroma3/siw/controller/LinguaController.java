package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import it.uniroma3.siw.service.LinguaService;

@Controller
public class LinguaController {
	@Autowired LinguaService linguaService;
	
	@GetMapping("/lingua/cinese")
	public String lingua(Model model) {
		model.addAttribute("lingua", this.linguaService.getLinguaById());
		return "primiPassi.html";
	}

}
