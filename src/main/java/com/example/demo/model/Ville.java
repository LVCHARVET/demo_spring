package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ville {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomVille;
	private Integer nbHabitants;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@NotNull
	private Departement departement;

	public Ville() {
	}

	public Ville(String nomVille, Integer nbHabitants, Departement departement) {
		this.nomVille = nomVille;
		this.nbHabitants = nbHabitants;
		this.departement = departement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public Integer getNbHabitants() {
	    return nbHabitants != null ? nbHabitants.intValue() : 10; // ou retourner une valeur par défaut
	}

	public void setNbHabitants(Integer nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
}