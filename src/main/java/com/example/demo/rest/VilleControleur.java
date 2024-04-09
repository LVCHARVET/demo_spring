package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ville;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    private List<Ville> villes;

    @PostConstruct
    public void init() {
        villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2243833));
        villes.add(new Ville("Marseille", 860363));
        villes.add(new Ville("Lyon", 513275));
    }

    @GetMapping
    public List<Ville> listeVille() {
        return villes;
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville nouvelleVille) {
        for (Ville ville : villes) {
            if (ville.getNom().equals(nouvelleVille.getNom())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("La ville est déjà enregistrée");
            }
        }

        villes.add(nouvelleVille);
        return ResponseEntity.status(HttpStatus.CREATED).body("La ville a été ajoutée avec succès !");
    }
}
