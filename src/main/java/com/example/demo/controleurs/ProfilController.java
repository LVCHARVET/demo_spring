package com.example.demo.controleurs;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

	@GetMapping("/profil")
	public String getIndex(Model model, Authentication authentication) {
		model.addAttribute("authentication", authentication);
		return "profil";
	}

}
