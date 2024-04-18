package com.example.demo.rest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.MapperUtils;
import com.example.demo.model.Ville;
import com.example.demo.modelDTO.VilleDto;
import com.example.demo.service.VilleService;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	@Autowired
	private VilleService villeService;

	@Autowired
	private MapperUtils mapperUtils;

	@GetMapping
	public List<VilleDto> getVilles() {
		List<Ville> villes = villeService.extractVilles();
		return mapperUtils.convertToDtoList(villes, VilleDto.class);
	}

	@GetMapping("/exportVillesCSV")
	public String exportVillesCSV() {

		List<Ville> villes = villeService.extractVilles();
		String csvFileName = "src/main/resources/csv/villes.csv";

		try (FileWriter writer = new FileWriter(csvFileName)) {

			writer.append("Nom de la ville,Nombre d'habitants,Code département,Nom du département\n");

			for (Ville ville : villes) {

				writer.append(String.join(",", ville.getNomVille(), String.valueOf(ville.getNbHabitants()),
						ville.getDepartement().getCode(), ville.getDepartement().getNomDepartement()));
				writer.append("\n");

			}

			return "CSV exporté avec succès!";

		} catch (IOException e) {

			e.printStackTrace();
			return "Erreur lors de l'export CSV: " + e.getMessage();

		}
	}

	@GetMapping("/{id}")
	public VilleDto getVilleById(@PathVariable Long id) {
		Ville ville = villeService.extractVille(id);
		return mapperUtils.convertToDto(ville, VilleDto.class);
	}

	@PutMapping("/{id}")
	public List<VilleDto> updateVille(@PathVariable Long id, @RequestBody VilleDto villeDtoModifiee) {
		Ville villeModifiee = mapperUtils.convertToEntity(villeDtoModifiee, Ville.class);
		return mapperUtils.convertToDtoList(villeService.modifierVille(id, villeModifiee), VilleDto.class);
	}

	@PostMapping
	public ResponseEntity<List<VilleDto>> addVille(@RequestBody VilleDto nouvelleVilleDto) {
		Ville nouvelleVille = mapperUtils.convertToEntity(nouvelleVilleDto, Ville.class);
		Ville villeAjoutee = villeService.insertVille(nouvelleVille);
		VilleDto villeAjouteeDto = mapperUtils.convertToDto(villeAjoutee, VilleDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonList(villeAjouteeDto));
	}

	@GetMapping("/delete/{id}")
	public List<VilleDto> deleteVille(@PathVariable Long id) {
		return mapperUtils.convertToDtoList(villeService.supprimerVille(id), VilleDto.class);
	}

	@GetMapping("/nom/{debutNom}")
	public List<VilleDto> listeVillesParNom(@PathVariable String debutNom) {
		List<Ville> villes = villeService.extractVillesByNomStartingWith(debutNom);
		return mapperUtils.convertToDtoList(villes, VilleDto.class);
	}

	@GetMapping("/population/min/{min}")
	public List<VilleDto> listeVillesPopulationMin(@PathVariable int min) {
		List<Ville> villes = villeService.extractVillesByNbHabitantsGreaterThan(min);
		return mapperUtils.convertToDtoList(villes, VilleDto.class);
	}

	@GetMapping("/population/{min}/{max}")
	public List<VilleDto> listeVillesPopulationEntre(@PathVariable int min, @PathVariable int max) {
		List<Ville> villes = villeService.extractVillesByNbHabitantsBetween(min, max);
		return mapperUtils.convertToDtoList(villes, VilleDto.class);
	}

	@GetMapping("/population/{codeDepartement}/min/{min}")
	public List<VilleDto> listeVillesPopulationDepartementMin(@PathVariable String codeDepartement,
			@PathVariable int min) {
		List<Ville> villes = villeService.extractVillesByCodeDepartementAndNbHabitantsGreaterThan(codeDepartement, min);
		return mapperUtils.convertToDtoList(villes, VilleDto.class);
	}

	@GetMapping("/population/{codeDepartement}/{min}/{max}")
	public List<VilleDto> listeVillesPopulationDepartementEntre(@PathVariable String codeDepartement,
			@PathVariable int min, @PathVariable int max) {
		List<Ville> villes = villeService.extractVillesByCodeDepartementAndNbHabitantsBetween(codeDepartement, min,
				max);
		return mapperUtils.convertToDtoList(villes, VilleDto.class);
	}

	@GetMapping("/find-population/{codeDep}/{size}")
	public Page<VilleDto> findByDepartmentCodeOrderByNbHabitantsDesc(@PathVariable("codeDep") String codeDep,
			@PathVariable("size") Integer size) {
		Page<Ville> villesPage = villeService.extractVillesByDepartmentCodeOrderByNbHabitantsDesc(codeDep, size);
		return villesPage.map(ville -> mapperUtils.convertToDto(ville, VilleDto.class));
	}
}