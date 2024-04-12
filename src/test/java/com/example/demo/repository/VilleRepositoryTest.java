package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Ville;
import com.example.demo.service.VilleService;

@SpringBootTest
@ActiveProfiles("test")
public class VilleRepositoryTest {

	@Autowired
	VilleService villeService;

	@MockBean
	VilleRepository villeRepository;

	@Test
	public void testFindByNomVilleStartingWith() {
		List<Ville> v = villeRepository.findByNomVilleStartingWith("Par");
		assertEquals(v.size(), 0);
		assertNotNull(v.get(0));
		assertEquals("Paris", v.get(0).getNomVille());
	}
}
