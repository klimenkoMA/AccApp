package recproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recproj.entity.PersonsProf;
import recproj.service.PersonsProfService;

import java.util.List;

@Controller
public class PersonsProfController {

	@Autowired
	PersonsProfService personsProfService;

	@PostMapping("/addpersonsprof")
	public String addPersonsProf(@RequestParam String name_rec, Model model) {
		PersonsProf personsProf = new PersonsProf(name_rec);
		personsProfService.addNewPersonsProf(personsProf);
		List<PersonsProf> personsProfList = personsProfService.getAllPersonsProf();
		model.addAttribute("personsProfList", personsProfList);
		return "personsprof";
	}

	@PostMapping("/deletepersonsprof")
	public String deletePersonsProf(@RequestParam int id, Model model) {
		personsProfService.deletePersonsProfById(id);
		List<PersonsProf> personsProfList = personsProfService.getAllPersonsProf();
		model.addAttribute("personsProfList", personsProfList);
		return "personsprof";
	}

	@PostMapping("/updatepersonsprof")
	public String updatePersonProf(@RequestParam int id, @RequestParam String name_rec, Model model) {
		PersonsProf personsProf = new PersonsProf(id, name_rec);
		personsProfService.updatePersonsProf(personsProf);
		List<PersonsProf> personsProfList = personsProfService.getAllPersonsProf();
		model.addAttribute("personsProfList", personsProfList);
		return "personsprof";
	}

	@PostMapping("findpersonsprofbyid")
	public String findPersonById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<PersonsProf> personsProfList = personsProfService.getPersonsProfById(findbyid);
			model.addAttribute("personsProfList", personsProfList);
		} else {
			List<PersonsProf> personsProfList = personsProfService.getAllPersonsProf();
			model.addAttribute("personsProfList", personsProfList);
		}
		return "personsprof";
	}

	@GetMapping("/allPersonsProf")
	public String getPersonsProf(Model model) {
		List<PersonsProf> personsProfList = personsProfService.getAllPersonsProf();
		model.addAttribute("personsProfList", personsProfList);
		return "personsprof";
	}
}
