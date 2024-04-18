package com.example.demo.component;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Departement;
import com.example.demo.model.Ville;
import com.example.demo.service.DepartementService;
import com.example.demo.service.VilleService;

@Component
public class CSVDataLoader implements CommandLineRunner {

	@Autowired
	private VilleService villeService;

	@Autowired
	private DepartementService departementService;

	public void run(String... args) throws Exception {
		String villesCsvFilePath = "villes.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(villesCsvFilePath))) {
			String line;
			boolean firstLineSkipped = false;
			while ((line = br.readLine()) != null) {
				if (!firstLineSkipped) {
					firstLineSkipped = true;
					continue;
				}
				String[] data = line.split(",");

				Ville ville = new Ville();
				ville.setNomVille(data[1]);

				Departement departement = departementService.getOrCreateDepartement(data[6], data[7]);

				if (departement != null) {
					ville.setDepartement(departement);
				}

				villeService.insertVille(ville);
			}
		}
	}
}