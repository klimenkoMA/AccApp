package accountingApp.controller;

import accountingApp.entity.Employee;
import accountingApp.entity.ITStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.service.ITStaffService;
import accountingApp.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ITStaffService ITStaffService;

	@GetMapping("/allPersons")
	public String getPersons(Model model) {
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "employee";
	}
@PostMapping("/updatePersons")
	public String updatePersons(@RequestParam int id,@RequestParam String fio, @RequestParam
			String login, @RequestParam String password,
	                            @RequestParam(required = false) Integer prof_id, Model model){
		List<ITStaff> ITStaffList = ITStaffService.getPersonsProfById(prof_id);
		Employee employee = new Employee(id, fio, login, password, ITStaffList.get(0));
		employeeService.updatePerson(employee);
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "employee";
	}

	@PostMapping("/allPersons")
	public String addPerson(@RequestParam String fio, @RequestParam
			String login, @RequestParam String password,
	                        @RequestParam(required = false) Integer prof_id, Model model) {
		List<ITStaff> ITStaffList = ITStaffService.getPersonsProfById(prof_id);
		Employee employee = new Employee(fio, login, password, ITStaffList.get(0));
		ITStaffService.addNewPersons(employee);
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "employee";
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
		return "employee";
	}

	@PostMapping("delete")
	public String deleteById(@RequestParam int delete, Model model) {
		if(delete > 0) {
			employeeService.deletePersonsById(delete);
		}
		List<Employee> employeeList = employeeService.getListPersons();
		model.addAttribute("personsList", employeeList);
		return "employee";
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
		return "employee";
	}
}
