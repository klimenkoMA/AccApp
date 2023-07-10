package accountingApp.controller;

import accountingApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.entity.PersonsProf;
import accountingApp.service.PersonsProfService;
import accountingApp.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	PersonsProfService personsProfService;

	@GetMapping("/allPersons")
	public String getPersons(Model model) {
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "persons";
	}
@PostMapping("/updatePersons")
	public String updatePersons(@RequestParam int id,@RequestParam String fio, @RequestParam
			String login, @RequestParam String password,
	                            @RequestParam(required = false) Integer prof_id, Model model){
		List<PersonsProf> personsProfList = personsProfService.getPersonsProfById(prof_id);
		Employee employee = new Employee(id, fio, login, password, personsProfList.get(0));
		employeeService.updatePerson(employee);
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "persons";
	}

	@PostMapping("/allPersons")
	public String addPerson(@RequestParam String fio, @RequestParam
			String login, @RequestParam String password,
	                        @RequestParam(required = false) Integer prof_id, Model model) {
		List<PersonsProf> personsProfList = personsProfService.getPersonsProfById(prof_id);
		Employee employee = new Employee(fio, login, password, personsProfList.get(0));
		personsProfService.addNewPersons(employee);
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "persons";
	}

	@PostMapping("findbyfio")
	public String findAuthorisation(@RequestParam String findbyfio,
	                                Model model) {
		if(findbyfio != null && !findbyfio.isEmpty()) {
			List<Employee> employeeList = employeeService.findPersonsByFio(findbyfio);
			model.addAttribute("personsList", employeeList);
		} else {
			getPersons(model);
		}
		return "persons";
	}

	@PostMapping("delete")
	public String deleteById(@RequestParam int delete, Model model) {
		if(delete > 0) {
			employeeService.deletePersonsById(delete);
		}
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "persons";
	}

	@PostMapping("findbyid")
	public String findPersonById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<Employee> employeeList = employeeService.findPersonsById(findbyid);
			model.addAttribute("personsList", employeeList);
		} else {
			List<Employee> employeeList = employeeService.getListPersons();
			model.addAttribute("personsList", employeeList);
		}
		return "persons";
	}
}
