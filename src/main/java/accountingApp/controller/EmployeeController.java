package accountingApp.controller;

import accountingApp.entity.Employee;
import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.RoomService;
import accountingApp.service.WorkAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RoomService roomService;
    @Autowired
    WorkAreaService workAreaService;

    @GetMapping("/employee")
    public String getEmployee(Model model) {
        List<Employee> employeeList = employeeService.getListEmployee();
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("workAreaList", workAreaList);
        model.addAttribute("roomList", roomList);
        return "employee";
    }

    @PostMapping("/addemployee")
    public String addEmployee(@RequestParam String fio,
                              @RequestParam String dborn,
                              @RequestParam WorkArea workarea,
                              @RequestParam Room room,
                              Model model) {
        String fioWithoutSpaces = fio.trim();
        String dbornWithoutSpaces = dborn.trim();
        String workAreaWithoutSpaces = workarea.getName();
        String roomWithoutSpaces = room.getNumber();
        try {

            int dbornCheck = Integer.parseInt(dbornWithoutSpaces);
            if (dbornCheck <= 0) {
                System.out.println("*** dborn <<<< 0 ***");
                return this.getEmployee(model);
            }
        } catch (Exception e) {
            System.out.println("*** EmployeeController.addEmployee(): wrong dborn's type! ***");
            System.out.println(e.getMessage());
            return this.getEmployee(model);
        }
        try {
            if (!fioWithoutSpaces.equals("")
                    && !dbornWithoutSpaces.equals("")
                    && !workAreaWithoutSpaces.equals("")
                    && !roomWithoutSpaces.equals("")) {

                Employee employee = new Employee(fioWithoutSpaces
                        , dbornWithoutSpaces
                        , workarea
                        , room);
                employeeService.addNewEmployee(employee);
                List<Employee> employeeList = employeeService.getListEmployee();
                model.addAttribute("employeeList", employeeList);
                return getEmployee(model);
            }
        } catch (Exception e) {
            System.out.println("*** EmployeeController.addEmployee(): wrong DB's values! ***");
            System.out.println(e.getMessage());
            return getEmployee(model);
        }
        return this.getEmployee(model);
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
            return this.getEmployee(model);

        } catch (Exception e) {
            System.out.println(e.toString() + "||| WRONG ID |||\n" + e.getMessage());
            return this.getEmployee(model);
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
                return this.getEmployee(model);
            }
        } catch (Exception e) {
            System.out.println("*** WRONG ID OR DBORN OR ROOM ***\n" + e.getMessage());
            return this.getEmployee(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (!fioWithoutSpaces.equals("") &&
                    !dbornWithoutSpaces.equals("") &&
                    !workAreaWithoutSpaces.equals("")) {
                List<Room> rooms = roomService
                        .getRoomById(Integer.parseInt(roomWithoutSpaces));
                List<WorkArea> workAreas = workAreaService
                        .getWorkAreaByName(workAreaWithoutSpaces);
                Employee employee = new Employee(idCheck,
                        fioWithoutSpaces,
                        dbornWithoutSpaces,
                        workAreas.get(0),
                        rooms.get(0));
                employeeService.updateEmployee(employee);
                List<Employee> employeeList = employeeService.getListEmployee();
                model.addAttribute("employeeList", employeeList);
                return this.getEmployee(model);
            }
        } catch (Exception e) {
            System.out.println("|||Something wrong in DB|||\n" + e.getMessage());
            return this.getEmployee(model);
        }
        return this.getEmployee(model);
    }


    @PostMapping("/findbyfio")
    public String findEmployeeByFio(@RequestParam String fio,
                                    Model model) {
        String fioWithoutSpaces = fio.trim();
        try {
            List<Employee> employeeList;
            int idCheck = Integer.parseInt(fioWithoutSpaces);
            if (idCheck <= 0 || fioWithoutSpaces.isEmpty()) {
                System.out.println("***SUB ZERO***");
                employeeList = employeeService.getListEmployee();
            } else {
                employeeList = employeeService.findEmployeeById(idCheck);
            }
            model.addAttribute("employeeList", employeeList);
            return "employee";
        } catch (Exception e) {
            List<Employee> employeeList = employeeService.findEmployeeByFio(fio);
            model.addAttribute("employeeList", employeeList);
            System.out.println("*** FIND BY NAME ***\n" + e.getMessage());
            return this.getEmployee(model);
        }
    }
}
