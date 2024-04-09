package com.example.demo.model;

public class Ville {
	

	private int id;
	private String nom;
	private int nbHabitants;
		
	public Ville(int id, String nom, int nbHabitants) {
		super();
		this.id = id;
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
