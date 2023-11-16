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

    @GetMapping("/employee")
    public String getEmployee(Model model) {
        List<Employee> employeeList = employeeService.getListEmployee();
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }

    @PostMapping("/addemployee")
    public String addEmployee(@RequestParam String fio,
                              @RequestParam String dborn,
                              @RequestParam String workArea,
                              @RequestParam String room,
                              Model model) {
        String fioWithoutSpaces = fio.trim();
        String dbornWithoutSpaces = dborn.trim();
        String workAreaWithoutSpaces = workArea.trim();
        String roomWithoutSpaces = room.trim();
        try {
            int roomCheck = Integer.parseInt(roomWithoutSpaces);
            int dbornCheck = Integer.parseInt(dbornWithoutSpaces);
            if (roomCheck <= 0 || dbornCheck <= 0) {
                System.out.println("*** ID or dborn <<<< 0***");
                return "employee";
            }
        } catch (Exception e) {
            System.out.println("***Wrong ID or dborn!***");
            return "employee";
        }
        try {
            if (!fioWithoutSpaces.equals("") &&
                    !dbornWithoutSpaces.equals("") &&
                    !workAreaWithoutSpaces.equals("")) {
                Employee employee = new Employee(fioWithoutSpaces,
                        dbornWithoutSpaces,
                        workAreaWithoutSpaces,
                        roomWithoutSpaces);
                employeeService.addNewEmployee(employee);
                List<Employee> employeeList = employeeService.getListEmployee();
                model.addAttribute("employeeList", employeeList);
                return "employee";
            }
        } catch (Exception e) {
            System.out.println("|||Something wrong in DB|||");
            return "employee";
        }
        return "employee";
    }

    @PostMapping("/deleteemployee")
    public String deleteById(@RequestParam String id
            , Model model) {

        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck > 0) {
                employeeService.deleteEmployeeById(idCheck);
            }
            List<Employee> employeeList = employeeService.getListEmployee();
            model.addAttribute("employeeList", employeeList);
            return "employee";

        } catch (Exception e) {
            System.out.println(e.toString() + "||| WRONG ID |||");
            return "employee";
        }
    }

    @PostMapping("/updateemployee")
    public String updateEmployee(@RequestParam String id,
                                 @RequestParam String fio,
                                 @RequestParam String dborn,
                                 @RequestParam String workarea,
                                 @RequestParam String room,
                                 Model model) {

        String idWithoutSpaces = id.trim();
        String fioWithoutSpaces = fio.trim();
        String dbornWithoutSpaces = dborn.trim();
        String workAreaWithoutSpaces = workarea.trim();
        String roomWithoutSpaces = room.trim();

        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            int dbornCheck = Integer.parseInt(dbornWithoutSpaces);
            int roomCheck = Integer.parseInt(roomWithoutSpaces);
            if (idCheck <= 0 ||
                    dbornCheck <= 0 ||
                    roomCheck <= 0) {
                System.out.println("*** SUB ZERO ***");
                return "employee";
            }
        } catch (Exception e) {
            System.out.println("*** WRONG ID OR DBORN OR ROOM ***");
            return "employee";
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (!fioWithoutSpaces.equals("") &&
                    !dbornWithoutSpaces.equals("") &&
                   !workAreaWithoutSpaces.equals("")) {

                Employee employee = new Employee(idCheck,
                        fioWithoutSpaces,
                        dbornWithoutSpaces,
                        workAreaWithoutSpaces,
                        roomWithoutSpaces);
                employeeService.updateEmployee(employee);
                List<Employee> employeeList = employeeService.getListEmployee();
                model.addAttribute("employeeList", employeeList);
                return "employee";
            }
        } catch (Exception e) {
            System.out.println("|||Something wrong in DB|||");
            return "employee";
        }
        return "employee";
    }


    @PostMapping("/findbyfio")
    public String findEmployeeByFio(@RequestParam String fio,
                                    Model model) {
        String fioWithoutSpaces = fio.trim();
        try{
            List<Employee> employeeList;
            int idCheck = Integer.parseInt(fioWithoutSpaces);
            if (idCheck <= 0 || fioWithoutSpaces.isEmpty()){
                System.out.println("***SUB ZERO***");
                employeeList = employeeService.getListEmployee();
            }else{
                employeeList = employeeService.findEmployeeById(idCheck);
            }
            model.addAttribute("employeeList", employeeList);
            return "employee";
        }catch (Exception e){
            List<Employee> employeeList = employeeService.findEmployeeByFio(fio);
            model.addAttribute("employeeList", employeeList);
            System.out.println("*** FIND BY NAME ***");
            return "employee";
        }
    }
}
