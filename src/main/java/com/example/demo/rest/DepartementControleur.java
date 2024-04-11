package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Departement;
import com.example.demo.service.DepartementService;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    @Autowired
    private DepartementService departementService;

    @GetMapping
    public List<Departement> getDepartements() {
        return departementService.extractDepartements();
    }

    @GetMapping("/{code}")
    public Departement getDepartementByCode(@PathVariable String code) {
        return departementService.extractDepartementByCode(code);
    }

    @PutMapping("/{code}")
	public List<Departement> updateDepartement(@PathVariable String code, @RequestBody Departement departementModifiee) {

		return departementService.modifierDepartement(code, departementModifiee);

	}

    @PostMapping
    public List<Departement> addDepartement(@RequestBody Departement nouveauDepartement) {
        return departementService.addDepartement(nouveauDepartement);
    }

    @DeleteMapping("/{code}")
    public List<Departement> deleteDepartement(@PathVariable String code) {
        return departementService.suppimerDepartement(code);
    }

    }