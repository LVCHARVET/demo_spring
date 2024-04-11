package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.exception.GestionErreurs;
import com.example.demo.mapper.MapperUtils;
import com.example.demo.model.Departement;
import com.example.demo.model.Ville;
import com.example.demo.modelDTO.VilleDto;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.repository.VilleRepository;

import jakarta.transaction.Transactional;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;
    
    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private MapperUtils mapperUtils;

    public List<VilleDto> extractVillesDto() {
        List<Ville> villes = villeRepository.findAll();
        return mapperUtils.convertToDtoList(villes, VilleDto.class);
    }

    public List<Ville> extractVilles() {
        return villeRepository.findAll();
    }

    public Ville extractVille(int idVille) {
        return villeRepository.findById(idVille).orElse(null);
    }

    @Transactional
    public Ville insertVille(Ville ville) {
        
        Departement departement = departementRepository.findByNomDepartement(ville.getDepartement().getNomDepartement());
        
        if (departement == null) {
            departement = new Departement(ville.getDepartement().getCode(), ville.getDepartement().getNomDepartement());
            departement = departementRepository.save(departement);
        }

        ville.setDepartement(departement);

        if (ville.getNbHabitants() < 10) {
            throw new GestionErreurs("Une ville doit avoir au moins 10 habitants");
        }
        if (ville.getNomVille().length() < 2) {
            throw new GestionErreurs("Le nom de la ville doit contenir au moins 2 lettres");
        }
        if (ville.getDepartement().getCode().length() != 2) {
            throw new GestionErreurs("Le code département doit comporter exactement 2 caractères");
        }
        if (villeRepository.existsByNomVilleAndDepartementNomDepartement(ville.getNomVille(), departement.getNomDepartement())) {
            throw new GestionErreurs("Le nom de la ville doit être unique pour un département donné");
        }

        return villeRepository.save(ville);
    }

    public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
        Ville ville = villeRepository.findById(idVille).orElse(null);

        if (ville != null) {
            ville.setNomVille(villeModifiee.getNomVille());
            ville.setNbHabitants(villeModifiee.getNbHabitants());
            villeRepository.save(ville);
        }

        return extractVilles();
    }

    public List<Ville> supprimerVille(int idVille) {
        villeRepository.deleteById(idVille);
        return extractVilles();
    }

    public List<Ville> extractVillesByNomStartingWith(String debutNom) {
        List<Ville> villes = villeRepository.findByNomVilleStartingWith(debutNom);
        if (villes.isEmpty()) {
            throw new GestionErreurs("Aucune ville dont le nom commence par \"" + debutNom + "\" n'a été trouvée");
        }
        return villes;
    }

    public List<Ville> extractVillesByNbHabitantsGreaterThan(int min) {
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(min);
        if (villes.isEmpty()) {
            throw new GestionErreurs("Aucune ville n'a une population supérieure à " + min);
        }
        return villes;
    }

    public List<Ville> extractVillesByNbHabitantsBetween(int min, int max) {
        List<Ville> villes = villeRepository.findByNbHabitantsBetween(min, max);
        if (villes.isEmpty()) {
            throw new GestionErreurs("Aucune ville n'a une population comprise entre " + min + " et " + max);
        }
        return villes;
    }

    public List<Ville> extractVillesByCodeDepartementAndNbHabitantsGreaterThan(String departementCode, int min) {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(departementCode, min);
        if (villes.isEmpty()) {
            throw new GestionErreurs("Aucune ville n'a une population supérieure à " + min + " dans le département " + departementCode);
        }
        return villes;
    }

    public List<Ville> extractVillesByCodeDepartementAndNbHabitantsBetween(String departementCode, int min, int max) {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsBetween(departementCode, min, max);
        if (villes.isEmpty()) {
            throw new GestionErreurs("Aucune ville n'a une population comprise entre " + min + " et " + max + " dans le département " + departementCode);
        }
        return villes;
    }

    public Page<Ville> extractVillesByDepartmentCodeOrderByNbHabitantsDesc(String departmentCode, Integer size) {
        return villeRepository.findByDepartementCodeOrderByNbHabitantsDesc(departmentCode, PageRequest.of(0, size));
    }
}