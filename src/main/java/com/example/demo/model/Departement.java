package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;
	private String nomDepartement;

	@OneToMany(mappedBy = "departement")
	private List<Ville> villes = new ArrayList<>();

	public Departement() {
	}

	public Departement(String code, String nomDepartement) {
		this.code = code;
		this.nomDepartement = nomDepartement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
}
