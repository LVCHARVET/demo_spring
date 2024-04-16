package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ville;

public interface VilleRepository extends JpaRepository<Ville, Integer> {

	List<Ville> findByNomVilleStartingWith(String debutNom);

	List<Ville> findByNbHabitantsGreaterThan(int min);

	List<Ville> findByNbHabitantsBetween(int min, int max);

	List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(String codeDepartement, int min);

	List<Ville> findByDepartementCodeAndNbHabitantsBetween(String codeDepartement, int min, int max);

	Page<Ville> findByDepartementCodeOrderByNbHabitantsDesc(String codeDepartement, Pageable pageable);

	boolean existsByNomVilleAndDepartementNomDepartement(String nomVille, String nomDepartement);

	@Query("SELECT v FROM Ville v JOIN FETCH v.departement")
	List<Ville> findAllWithDepartments();
}