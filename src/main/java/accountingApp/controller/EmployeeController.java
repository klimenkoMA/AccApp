package accountingApp.controller;

import accountingApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.ITStaffService;
import accountingApp.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ITStaffService ITStaffService;

    @GetMapping("/employee") //allPersons
    public String getEmployee(Model model) {
        List<Employee> employeeList = employeeService.getListEmployee();
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }

    @PostMapping("/addemployee") //allPersons
    public String addEmployee(@RequestParam String fio,
                              @RequestParam String dborn,
                              @RequestParam String workArea,
                              @RequestParam String room,
                              Model model) {
        Employee employee = new Employee(fio, dborn, workArea, room);
        employeeService.addNewEmployee(employee);
        List<Employee> employeeList = employeeService.getListEmployee();
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }

    	@PostMapping("/deleteemployee")
	public String deleteById(@RequestParam int id
                , Model model) {
		if(id > 0) {
			employeeService.deleteEmployeeById(id);
		}
		List<Employee> employeeList = employeeService.getListEmployee();
		model.addAttribute("employeeList", employeeList);
		return "employee";
	}

//@PostMapping("/updatePersons")
//	public String updatePersons(@RequestParam int id, @RequestParam String fio, @RequestParam
//			String dborn, @RequestParam String workArea,
//								@RequestParam(required = false) Integer room, Model model){
//		List<ITStaff> ITStaffList = ITStaffService.getPersonsProfById(room);
//		Employee employee = new Employee(id, fio, dborn, workArea, ITStaffList.get(0));
//		employeeService.updateEmployee(employee);
//		List<Employee> employeeList = employeeService.getListEmployee();
//		model.addAttribute("employeeList", employeeList);
//		return "employee";
//	}


//	@PostMapping("findbyfio")
//	public String findAuthorisation(@RequestParam String findbyfio,
//	                                Model model) {
//		if(findbyfio != null && !findbyfio.isEmpty()) {
//			List<Employee> employeeList = employeeService.findEmployeeByFio(findbyfio);
//			model.addAttribute("employeeList", employeeList);
//		} else {
//			getPersons(model);
//		}
//		return "employee";
//	}
//
//
//	@PostMapping("findbyid")
//	public String findPersonById(@RequestParam int findbyid, Model model) {
//
//		if(findbyid > 0) {
//			List<Employee> employeeList = employeeService.findEmployeeById(findbyid);
//			model.addAttribute("employeeList", employeeList);
//		} else {
//			List<Employee> employeeList = employeeService.getListEmployee();
//			model.addAttribute("employeeList", employeeList);
//		}
//		return "employee";
//	}
}
