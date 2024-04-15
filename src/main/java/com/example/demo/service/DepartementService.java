package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.GestionErreurs;
import com.example.demo.mapper.MapperUtils;
import com.example.demo.model.Departement;
import com.example.demo.modelDTO.DepartementDto;
import com.example.demo.repository.DepartementRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartementService {

	@Autowired
	private DepartementRepository departementRepository;

	@Autowired
	private MapperUtils mapperUtils;

	public DepartementDto getDepartementByCode(String code) {
		Departement departement = departementRepository.findByCode(code);
		return mapperUtils.convertToDto(departement, DepartementDto.class);
	}

	public List<Departement> extractDepartements() {
		return departementRepository.findAll();
	}

	public Departement extractDepartementByCode(String code) {
		return departementRepository.findByCode(code);
	}

	public Departement getDepartementById(int id) {
		return departementRepository.findById(id).orElse(null);
	}

	@Transactional
	public Departement addDepartement(Departement departement) {

		if (departement.getCode().length() < 2 || departement.getCode().length() > 3) {
			throw new GestionErreurs("Le code département doit comporter entre 2 et 3 caractères");
		}
		if (departement.getNomDepartement() == null || departement.getNomDepartement().length() < 3) {
			throw new GestionErreurs("Le nom du département est obligatoire et doit comporter au moins 3 lettres");
		}
		if (departementRepository.existsByCode(departement.getCode())) {
			throw new GestionErreurs("Un département avec le même code existe déjà");
		}
		return departementRepository.save(departement);
	}

	public List<Departement> modifierDepartement(String codeDepartement, Departement departementModifiee) {

		Departement departement = departementRepository.findByCode(codeDepartement);

		if (departement != null) {

			departement.setNomDepartement(departementModifiee.getNomDepartement());
			departement.setCode(departementModifiee.getCode());
			departementRepository.save(departement);

		}

		return extractDepartements();

	}

	public List<Departement> suppimerDepartement(String code) {
		departementRepository.findByCode(code);
		return extractDepartements();
	}
	
	public Departement getOrCreateDepartement(String nomDepartement, String code) {
        Departement departement = departementRepository.findByNomDepartement(nomDepartement);
        if (departement == null) {
            departement = new Departement();
            departement.setNomDepartement(nomDepartement);
            departement.setCode(code);
            departementRepository.save(departement);
        }
        return departement;
    }
}