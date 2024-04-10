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

	public Ville() {
	}

	public Ville(String nom, int nbHabitants) {

		this.nom = nom;
		this.nbHabitants = nbHabitants;

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

	@Override
	public String toString() {

		return "Ville [nom=" + nom + ", nbHabitants=" + nbHabitants + "]";

	}

}
