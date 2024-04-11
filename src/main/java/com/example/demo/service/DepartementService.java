package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapperUtils;
import com.example.demo.model.Departement;
import com.example.demo.modelDTO.DepartementDto;
import com.example.demo.repository.DepartementRepository;

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

	public List<Departement> addDepartement(Departement departement) {
		departementRepository.save(departement);
		return extractDepartements();
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
}