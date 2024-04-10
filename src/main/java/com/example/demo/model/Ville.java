package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ville {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private int nbHabitants;
	private String codeDepartement;

	public Ville() {
	}

	public Ville(String nom, int nbHabitants, String codeDepartement) {

		this.nom = nom;
		this.nbHabitants = nbHabitants;
		this.codeDepartement = codeDepartement;

	}

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public String getNom() {

		return nom;

	}

	public void setNom(String nom) {

		this.nom = nom;

	}

	public int getNbHabitants() {

		return nbHabitants;

	}

	public void setNbHabitants(int nbHabitants) {

		this.nbHabitants = nbHabitants;

	}

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", nbHabitants=" + nbHabitants + ", codeDepartement="
				+ codeDepartement + "]";
	}

}
