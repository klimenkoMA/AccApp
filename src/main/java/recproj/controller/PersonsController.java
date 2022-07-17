package recproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recproj.entity.Persons;
import recproj.entity.PersonsProf;
import recproj.service.PersonsProfService;
import recproj.service.PersonsService;

import java.util.List;

@Controller
public class PersonsController {
	@Autowired
	PersonsService personsService;
	@Autowired
	PersonsProfService personsProfService;

	@GetMapping("/allPersons")
	public String getPersons(Model model) {
		List<Persons> personsList = personsService.getListPersons();
		model.addAttribute("personsList", personsList);
		return "persons";
	}
@PostMapping("/updatePersons")
	public String updatePersons(@RequestParam int id,@RequestParam String fio, @RequestParam
			String login, @RequestParam String password,
	                            @RequestParam(required = false) Integer prof_id, Model model){
		List<PersonsProf> personsProfList = personsProfService.getPersonsProfById(prof_id);
		Persons persons = new Persons(id, fio, login, password, personsProfList.get(0));
		personsService.updatePerson(persons);
		List<Persons> personsList = personsService.getListPersons();
		model.addAttribute("personsList", personsList);
		return "persons";
	}

	@PostMapping("/allPersons")
	public String addPerson(@RequestParam String fio, @RequestParam
			String login, @RequestParam String password,
	                        @RequestParam(required = false) Integer prof_id, Model model) {
		List<PersonsProf> personsProfList = personsProfService.getPersonsProfById(prof_id);
		Persons persons = new Persons(fio, login, password, personsProfList.get(0));
		personsProfService.addNewPersons(persons);
		List<Persons> personsList = personsService.getListPersons();
		model.addAttribute("personsList", personsList);
		return "persons";
	}

	@PostMapping("findbyfio")
	public String findAuthorisation(@RequestParam String findbyfio,
	                                Model model) {
		if(findbyfio != null && !findbyfio.isEmpty()) {
			List<Persons> personsList = personsService.findPersonsByFio(findbyfio);
			model.addAttribute("personsList", personsList);
		} else {
			getPersons(model);
		}
		return "persons";
	}

	@PostMapping("delete")
	public String deleteById(@RequestParam int delete, Model model) {
		if(delete > 0) {
			personsService.deletePersonsById(delete);
		}
		List<Persons> personsList = personsService.getListPersons();
		model.addAttribute("personsList", personsList);
		return "persons";
	}

	@PostMapping("findbyid")
	public String findPersonById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<Persons> personsList = personsService.findPersonsById(findbyid);
			model.addAttribute("personsList", personsList);
		} else {
			List<Persons> personsList = personsService.getListPersons();
			model.addAttribute("personsList", personsList);
		}
		return "persons";
	}
}
