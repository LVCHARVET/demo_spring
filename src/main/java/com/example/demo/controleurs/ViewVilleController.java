package com.example.demo.controleurs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Ville;
import com.example.demo.service.VilleService;

@Controller
public class ViewVilleController {

	@Autowired
	private VilleService villeService;

	@GetMapping("/viewvilles")
	public ModelAndView getVilles() {
		Map<String, Object> model = new HashMap<>();
		List<Ville> villes = villeService.extractVillesWithDepartments(); 
		model.put("villes", villes);

		return new ModelAndView("viewvilles", model);
	}

}
