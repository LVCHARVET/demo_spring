package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

	Departement findByCode(String code);
	
	boolean existsByCode(String code);

	Departement findByNomDepartement(String nomDepartement);
}
