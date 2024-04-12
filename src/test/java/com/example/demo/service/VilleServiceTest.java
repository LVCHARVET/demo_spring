package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Departement;
import com.example.demo.model.Ville;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.repository.VilleRepository;

@SpringBootTest
@ActiveProfiles("test")
public class VilleServiceTest {

	@Autowired
	private VilleService villeService;

	@MockBean
	private VilleRepository villeRepository;

	@MockBean
	private DepartementRepository departementRepository;

	@Test
	public void extractVilleTest() {
		Departement departement = new Departement("69", "Rhone");
		Ville ville1 = new Ville("Paris", 1000000, departement);
		Ville ville2 = new Ville("Lyon", 500000, departement);
		Mockito.when(villeRepository.findAll()).thenReturn(List.of(ville1, ville2));

		Iterable<Ville> villes = villeService.extractVilles();

		assertTrue(villes.iterator().hasNext());
	}

	@Test
	public void extractVillesTest() {
		Departement departement = new Departement("69", "Rhone");
		Ville ville1 = new Ville("Paris", 1000000, departement);
		Ville ville2 = new Ville("Lyon", 500000, departement);
		List<Ville> villesExpected = List.of(ville1, ville2);

		Mockito.when(villeRepository.findAll()).thenReturn(villesExpected);

		List<Ville> villesActual = villeService.extractVilles();

		assertEquals(villesExpected.size(), villesActual.size());
		for (int i = 0; i < villesExpected.size(); i++) {
			Ville villeExpected = villesExpected.get(i);
			Ville villeActual = villesActual.get(i);
			assertEquals(villeExpected.getNomVille(), villeActual.getNomVille());
			assertEquals(villeExpected.getNbHabitants(), villeActual.getNbHabitants());
			assertEquals(villeExpected.getDepartement(), villeActual.getDepartement());
		}

		Mockito.verify(villeRepository).findAll();
	}

	@Test
	public void insertVilleTest() {
	    Departement departement = new Departement("69", "Rhone");
	    Ville ville = new Ville("Lyon", 1000000, departement);

	    Mockito.when(departementRepository.findByNomDepartement(departement.getNomDepartement())).thenReturn(departement);

	    Mockito.when(villeRepository.existsByNomVilleAndDepartementNomDepartement(ville.getNomVille(),
	            departement.getNomDepartement())).thenReturn(false);

	    Mockito.when(villeRepository.save(ville)).thenReturn(ville);

	    Ville villeAjoutee = villeService.insertVille(ville);

	    assertEquals(ville, villeAjoutee);

	    verify(departementRepository).findByNomDepartement(departement.getNomDepartement());
	    verify(villeRepository).existsByNomVilleAndDepartementNomDepartement(ville.getNomVille(),
	            departement.getNomDepartement());
	    verify(villeRepository).save(ville);
	}	

}
