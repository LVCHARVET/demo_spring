package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapperUtils;
import com.example.demo.model.Ville;
import com.example.demo.modelDTO.VilleDto;
import com.example.demo.repository.VilleRepository;

@Service
public class VilleService {

	@Autowired
	private VilleRepository villeRepository;
	
	@Autowired
    private MapperUtils mapperUtils; 

    public List<VilleDto> extractVillesDto() {
        List<Ville> villes = villeRepository.findAll();
        return mapperUtils.convertToDtoList(villes, VilleDto.class);
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

	public List<Ville> extractVillesByCodeDepartementAndNbHabitantsGreaterThan(String departementCode, int min) {
		return villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(departementCode, min);
	}

	public List<Ville> extractVillesByCodeDepartementAndNbHabitantsBetween(String departementCode, int min, int max) {
		return villeRepository.findByDepartementCodeAndNbHabitantsBetween(departementCode, min, max);
	}

	public Iterable<Ville> findByDepartmentCodeOrderByNbInhabitantsDesc(String departmentCode, Integer size) {
		return villeRepository.findByDepartementCodeOrderByNbHabitantsDesc(departmentCode, Pageable.ofSize(size))
				.getContent();
	}
}
