package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ville;
import com.example.demo.service.VilleService;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	@Autowired
	private VilleService villeService;

	@GetMapping
	public List<Ville> getVilles() {

		return villeService.extractVilles();

	}

	@GetMapping("/{id}")
	public Ville getVilleById(@PathVariable int id) {

		return villeService.extractVille(id);

	}

	@PutMapping("/{id}")
	public List<Ville> updateVille(@PathVariable int id, @RequestBody Ville villeModifiee) {

		return villeService.modifierVille(id, villeModifiee);

	}

	@PostMapping
	public List<Ville> addVille(@RequestBody Ville nouvelleVille) {

		return villeService.insertVille(nouvelleVille);

	}

	@DeleteMapping("/{id}")
	public List<Ville> deleteVille(@PathVariable int id) {

		return villeService.supprimerVille(id);

	}

	@GetMapping("/nom/{debutNom}")
	public List<Ville> listeVillesParNom(@PathVariable String debutNom) {

		return villeService.extractVillesByNomStartingWith(debutNom);

	}

	@GetMapping("/population/min/{min}")
	public List<Ville> listeVillesPopulationMin(@PathVariable int min) {

		return villeService.extractVillesByNbHabitantsGreaterThan(min);

	}

	@GetMapping("/population/{min}/{max}")
	public List<Ville> listeVillesPopulationEntre(@PathVariable int min, @PathVariable int max) {

		return villeService.extractVillesByNbHabitantsBetween(min, max);

	}

	@GetMapping("/population/{codeDepartement}/min/{min}")
	public List<Ville> listeVillesPopulationDepartementMin(@PathVariable String codeDepartement,
			@PathVariable int min) {

		return villeService.extractVillesByCodeDepartementAndNbHabitantsGreaterThan(codeDepartement, min);

	}

	@GetMapping("/population/{codeDepartement}/{min}/{max}")
	public List<Ville> listeVillesPopulationDepartementEntre(@PathVariable String codeDepartement,
			@PathVariable int min, @PathVariable int max) {

		return villeService.extractVillesByCodeDepartementAndNbHabitantsBetween(codeDepartement, min, max);

	}

	@GetMapping("/{codeDepartement}/top/{n}")
	public List<Ville> topNVillesParDepartement(@PathVariable String codeDepartement, @PathVariable int n) {
	    return villeService.extractTopNVillesByDepartement(codeDepartement, n);
	}
}