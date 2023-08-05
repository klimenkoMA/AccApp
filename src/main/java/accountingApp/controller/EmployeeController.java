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
        if (id > 0) {
            employeeService.deleteEmployeeById(id);
        }
        List<Employee> employeeList = employeeService.getListEmployee();
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }

    @PostMapping("/updateemployee")
    public String updateEmployee(@RequestParam int id,
                                 @RequestParam String fio,
                                 @RequestParam String dborn,
                                 @RequestParam String workarea,
                                 @RequestParam String room,
                                 Model model) {

        Employee employee = new Employee(id, fio, dborn, workarea, room);
        employeeService.updateEmployee(employee);
        List<Employee> employeeList = employeeService.getListEmployee();
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }


    @PostMapping("/findbyfio")
    public String findEmployeeByFio(@RequestParam String fio,
                                    Model model) {
        List<Employee> employeeList = employeeService.findEmployeeByFio(fio);
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }


    @PostMapping("/findbyid")
    public String findEmployeeById(@RequestParam int id,
                                   Model model) {

        List<Employee> employeeList;
        if (id > 0) {
            employeeList = employeeService.findEmployeeById(id);
        } else {
            employeeList = employeeService.getListEmployee();
        }
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }
}
