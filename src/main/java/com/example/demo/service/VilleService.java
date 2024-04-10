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
		villeRepository.save(new Ville("Paris", 2243833, "75"));
		villeRepository.save(new Ville("Marseille", 860363, "13"));
		villeRepository.save(new Ville("Lyon", 513275, "69"));
	}

	public List<Ville> extractVilles() {

		return villeRepository.findAll();

	}

	public Ville extractVille(int idVille) {

		return villeRepository.findById(idVille).orElse(null);

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

	public List<Ville> extractVillesByNomStartingWith(String debutNom) {
		return villeRepository.findByNomStartingWith(debutNom);
	}

	public List<Ville> extractVillesByNbHabitantsGreaterThan(int min) {
		return villeRepository.findByNbHabitantsGreaterThan(min);
	}

	public List<Ville> extractVillesByNbHabitantsBetween(int min, int max) {
		return villeRepository.findByNbHabitantsBetween(min, max);
	}

	public List<Ville> extractVillesByCodeDepartementAndNbHabitantsGreaterThan(String codeDepartement, int min) {
		return villeRepository.findByCodeDepartementAndNbHabitantsGreaterThan(codeDepartement, min);
	}

	public List<Ville> extractVillesByCodeDepartementAndNbHabitantsBetween(String codeDepartement, int min, int max) {
		return villeRepository.findByCodeDepartementAndNbHabitantsBetween(codeDepartement, min, max);
	}
	
	public List<Ville> extractTopNVillesByDepartement(String codeDepartement, int n) {
	    return villeRepository.findTopNVillesByCodeDepartementOrderByNbHabitantsDesc(codeDepartement, n);
	}
}
