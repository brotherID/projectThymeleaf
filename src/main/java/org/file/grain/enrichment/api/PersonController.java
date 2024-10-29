package org.file.grain.enrichment.api;

import java.util.List;

import javax.xml.ws.RespectBinding;

import org.file.grain.enrichment.model.Person;
import org.file.grain.enrichment.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
//@RestController
@AllArgsConstructor
@Slf4j
public class PersonController {

	private PersonService personService;

	@PostMapping(path = "/add/personne")
	public Person savePerson(@RequestBody Person person) {

		return personService.addPersonne(person);

	}

//	@GetMapping(path = "/get/personne")
//	public Person findById(@RequestParam(value = "idPersonne") Long idPersonne) {
//
//		return personService.findPersonById(idPersonne);
//	}

	@GetMapping("/personnes")
	public String findAllPersonnes(Model model, 
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "5") int size,
			@RequestParam(value = "keyword", defaultValue = "") String keyword) {
		Page<Person> pagePersons = personService.findByFirstNameContainsIgnoreCase(keyword,page, size);
		model.addAttribute("persons", pagePersons.getContent());
		model.addAttribute("pages", new int[pagePersons.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", keyword);
		return "personnes";
	}

	@GetMapping("/deletePerson")
	public String deletePatient(@RequestParam(value = "id") Long id,String keyword,int page) {
		personService.deletePerson(id);
		return "redirect:/personnes?page="+page+"&keyword="+keyword;
	}

}
