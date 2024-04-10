package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ville;
import com.example.demo.repository.VilleRepository;

import jakarta.annotation.PostConstruct;

@Service
public class VilleService {

	@Autowired
	private VilleRepository villeRepository;

	@PostConstruct
	public void init() {
		villeRepository.save(new Ville("Paris", 2243833));
		villeRepository.save(new Ville("Marseille", 860363));
		villeRepository.save(new Ville("Lyon", 513275));
	}

	public List<Ville> extractVilles() {

		return villeRepository.findAll();

	}

	public Ville extractVille(int idVille) {

		return villeRepository.findById(idVille).orElse(null);

	}

	public Ville extractVille(String nom) {

		return villeRepository.findByNom(nom);

	}

	public List<Ville> insertVille(Ville ville) {

		villeRepository.save(ville);

		return extractVilles();

	}

	public List<Ville> modifierVille(int idVille, Ville villeModifiee) {

		Ville ville = villeRepository.findById(idVille).orElse(null);

		if (ville != null) {

			ville.setNom(villeModifiee.getNom());
			ville.setNbHabitants(villeModifiee.getNbHabitants());
			villeRepository.save(ville);

		}

		return extractVilles();

	}

	public List<Ville> supprimerVille(int idVille) {

		villeRepository.deleteById(idVille);

		return extractVilles();

	}
}
