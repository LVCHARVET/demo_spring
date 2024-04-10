package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Ville;

public interface VilleRepository extends JpaRepository<Ville, Integer> {

	List<Ville> findByNomStartingWith(String debutNom);

	List<Ville> findByNbHabitantsGreaterThan(int min);

	List<Ville> findByNbHabitantsBetween(int min, int max);

	List<Ville> findByCodeDepartementAndNbHabitantsGreaterThan(String codeDepartement, int min);

	List<Ville> findByCodeDepartementAndNbHabitantsBetween(String codeDepartement, int min, int max);

	@Query("SELECT v FROM Ville v WHERE v.codeDepartement = ?1 ORDER BY v.nbHabitants DESC")
	List<Ville> findTopNVillesByCodeDepartementOrderByNbHabitantsDesc(String codeDepartement, int n);
}