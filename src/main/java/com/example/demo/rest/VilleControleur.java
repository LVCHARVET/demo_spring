package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	@GetMapping	
	public List<Ville> listeVille() {
		List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2243833));
        villes.add(new Ville("Marseille", 860363));
        villes.add(new Ville("Lyon", 513275));
        return villes;
	}
	
}
