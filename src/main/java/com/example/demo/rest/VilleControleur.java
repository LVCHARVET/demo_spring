package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ville;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	private List<Ville> villes;
	private int nextId = 3;

	@PostConstruct
	public void init() {

		villes = new ArrayList<>();
		villes.add(new Ville(0, "Paris", 2243833));
		villes.add(new Ville(1, "Marseille", 860363));
		villes.add(new Ville(2, "Lyon", 513275));

	}

	@GetMapping
	public List<Ville> listeVille() {

		return villes;

	}

	@PostMapping
	public ResponseEntity<String> initVille(@RequestBody Ville nouvelleVille) {

		nouvelleVille.setId(nextId);
		nextId++;

		for (Ville ville : villes) {

			if (ville.getNom().equals(nouvelleVille.getNom())) {

				return ResponseEntity.status(HttpStatus.CONFLICT).body("La ville est déjà enregistrée");

			}
		}

		villes.add(nouvelleVille);

		return ResponseEntity.status(HttpStatus.CREATED).body("La ville a été ajoutée avec succès !");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ville> getVilleById(@PathVariable int id) {

		for (Ville ville : villes) {

			if (ville.getId() == id) {

				return ResponseEntity.ok(ville);

			}
		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateVille(@PathVariable int id, @RequestBody Ville villeModifiee) {

		for (int i = 0; i < villes.size(); i++) {

			Ville ville = villes.get(i);

			if (ville.getId() == id) {

				ville.setNom(villeModifiee.getNom());
				ville.setNbHabitants(villeModifiee.getNbHabitants());

				return ResponseEntity.ok("La ville a été modifiée avec succès !");

			}

		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id) {

		for (int i = 0; i < villes.size(); i++) {

			Ville ville = villes.get(i);

			if (ville.getId() == id) {

				villes.remove(i);
				return ResponseEntity.ok("La ville a été supprimée avec succès !");

			}

		}

		return ResponseEntity.notFound().build();

	}
}
