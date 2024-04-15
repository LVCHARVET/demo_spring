package com.example.demo.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.Departement;
import com.example.demo.model.Ville;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.repository.VilleRepository;
import com.example.demo.service.VilleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleControleurTest {

	@Autowired
	ModelMapper modelMapper;

	@MockBean
	VilleRepository villeRepository;

	@MockBean
	DepartementRepository departementRepository;

	@InjectMocks
	private VilleService villeService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void extractVilleTest() throws Exception {
		Departement departement = new Departement("69", "Rhone");
		Ville ville1 = new Ville("Paris", 1000000, departement);
		Ville ville2 = new Ville("Lyon", 500000, departement);
		when(villeRepository.findAll()).thenReturn(List.of(ville1, ville2));

		mockMvc.perform(MockMvcRequestBuilders.get("/villes")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Paris")));
	}

	@Test
	public void insertVilleTest() throws Exception {
		Departement departement = new Departement("28", "Centre");
		Ville ville = new Ville("Chartres", 44444, departement);

		when(departementRepository.findByNomDepartement(departement.getNomDepartement())).thenReturn(departement);
		when(villeRepository.existsByNomVilleAndDepartementNomDepartement(ville.getNomVille(),
				departement.getNomDepartement())).thenReturn(false);
		when(villeService.insertVille(Mockito.any(Ville.class))).thenReturn(ville);

		mockMvc.perform(MockMvcRequestBuilders.post("/villes").contentType("application/json")
				.content(objectMapper.writeValueAsString(ville))).andExpect(status().isOk());

	}
}
