package com.example.demo.rest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.model.Departement;
import com.example.demo.modelDTO.DepartementDto;
import com.example.demo.service.DepartementService;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

	@Autowired
	private DepartementService departementService;

	@Autowired
	private MapperUtils mapperUtils;

	@GetMapping("/exportDepartementsCSV")
	public String exportDepartementsCSV() {
		
		List<Departement> departements = departementService.extractDepartements();
		String csvFileName = "src/main/resources/csv/departements.csv";
		
		try (FileWriter writer = new FileWriter(csvFileName)) {
			
			writer.append("Code département,Nom du département\n");
			
			for (Departement departement : departements) {
				
				writer.append(String.join(",", departement.getCode(), departement.getNomDepartement()));
				writer.append("\n");
				
			}
			
			return "CSV exporté avec succès!";
			
		} catch (IOException e) {
			
			e.printStackTrace();			
			return "Erreur lors de l'export CSV: " + e.getMessage();
			
		}
	}

	@GetMapping
	public List<DepartementDto> getDepartements() {
		List<Departement> departements = departementService.extractDepartements();
		return mapperUtils.convertToDtoList(departements, DepartementDto.class);
	}

	@GetMapping("/{code}")
	public DepartementDto getDepartementByCode(@PathVariable String code) {
		Departement departement = departementService.extractDepartementByCode(code);
		return mapperUtils.convertToDto(departement, DepartementDto.class);
	}

	@PutMapping("/{code}")
	public List<DepartementDto> updateDepartement(@PathVariable String code,
			@RequestBody DepartementDto departementDtoModifiee) {
		Departement departementModifiee = mapperUtils.convertToEntity(departementDtoModifiee, Departement.class);
		return mapperUtils.convertToDtoList(departementService.modifierDepartement(code, departementModifiee),
				DepartementDto.class);
	}

	@PostMapping
	public ResponseEntity<List<DepartementDto>> addDepartement(@RequestBody DepartementDto nouveauDepartementDto) {
		Departement nouveauDepartement = mapperUtils.convertToEntity(nouveauDepartementDto, Departement.class);
		Departement departementAjoutee = departementService.addDepartement(nouveauDepartement);
		DepartementDto departementDtos = mapperUtils.convertToDto(departementAjoutee, DepartementDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonList(departementDtos));

	}

	@DeleteMapping("/{code}")
	public List<DepartementDto> deleteDepartement(@PathVariable String code) {
		return mapperUtils.convertToDtoList(departementService.suppimerDepartement(code), DepartementDto.class);
	}
}