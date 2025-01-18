package accountingApp.controller;

import accountingApp.entity.Employee;
import accountingApp.entity.Profession;
import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.RoomService;
import accountingApp.service.WorkAreaService;
import accountingApp.usefulmethods.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private WorkAreaService workAreaService;
    @Autowired
    private Checker checker;

    @GetMapping("/employee")
    public String getEmployee(Model model) {
        List<Employee> employeeList = employeeService.getListEmployee();
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        List<Room> roomList = roomService.findAllRoom();

        Profession[] professionsArray = Profession.values();
        List<String> professionList = new ArrayList<>();

        for (Profession prf : professionsArray
        ) {
            professionList.add(prf.getProfession());
        }

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("workAreaList", workAreaList);
        model.addAttribute("roomList", roomList);
        model.addAttribute("professionList", professionList);
        return "employee";
    }

    @PostMapping("/addemployee")
    public String addEmployee(@RequestParam String fio,
                              @RequestParam String dborn,
                              @RequestParam String profession,
                              @RequestParam(required = false) WorkArea workarea,
                              @RequestParam(required = false) Room room,
                              Model model) {

        if (checker.checkAttribute(fio)
                || checker.checkAttribute(dborn)
                || checker.checkAttribute(profession)
                || workarea == null
                || room == null
        ) {
            logger.warn("*** EmployeeController.addEmployee():" +
                    "  Attribute has a null value! ***");
            return getEmployee(model);
        }

        String fioWithoutSpaces = fio.trim();
        String dbornWithoutSpaces = dborn.trim();
        String workAreaWithoutSpaces = workarea.getName();
        String roomWithoutSpaces = room.getNumber();

        try {
            if (!checker.checkAttribute(fioWithoutSpaces)
                    && !checker.checkAttribute(dbornWithoutSpaces)
                    && !checker.checkAttribute(workAreaWithoutSpaces)
                    && !checker.checkAttribute(roomWithoutSpaces)
            ) {
                Profession prof = Profession.Преподаватель;
                Profession[] professionsArray = Profession.values();

                for (Profession prf : professionsArray
                ) {
                    if (prf.getProfession().equals(profession)) {
                        prof = prf;
                        break;
                    }
                }

                Employee employee = new Employee(fioWithoutSpaces
                        , dbornWithoutSpaces
                        , prof
                        , workarea
                        , room);
                employeeService.addNewEmployee(employee);
                return getEmployee(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            logger.error("*** EmployeeController.addEmployee(): wrong DB's values! *** "
                    + e.getMessage());
            return getEmployee(model);
        }
    }

    @PostMapping("/deleteemployee")
    public String deleteById(@RequestParam String id
            , Model model) {

        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** EmployeeController.deleteById():" +
                    "  Attribute ID has a null value! ***");
            return getEmployee(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck > 0) {
                employeeService.deleteEmployeeById(idCheck);
            }
            return getEmployee(model);
        } catch (Exception e) {
            logger.error("*** EmployeeController.deleteById(): wrong DB's values! *** "
                    + e.getMessage());
            return getEmployee(model);
        }
    }

    @PostMapping("/updateemployee")
    public String updateEmployee(@RequestParam String id,
                                 @RequestParam String fio,
                                 @RequestParam String dborn,
                                 @RequestParam String profession,
                                 @RequestParam(required = false) WorkArea workarea,
                                 @RequestParam(required = false) Room room,
                                 Model model) {

        if (checker.checkAttribute(id)
                || checker.checkAttribute(fio)
                || checker.checkAttribute(dborn)
                || checker.checkAttribute(profession)
                || workarea == null
                || room == null
        ) {
            logger.warn("*** EmployeeController.updateEmployee():" +
                    "  Attribute has a null value! ***");
            return getEmployee(model);
        }

        String idWithoutSpaces = id.trim();
        String fioWithoutSpaces = fio.trim();
        String dbornWithoutSpaces = dborn.trim();
        String workAreaWithoutSpaces = workarea.getName();
        String roomWithoutSpaces = room.getNumber();

        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);

            if (idCheck <= 0) {
                logger.warn("*** EmployeeController.updateEmployee(): sub-zero ID values! ***");
                return getEmployee(model);
            }
        } catch (Exception e) {
            logger.error("*** EmployeeController.updateEmployee(): WRONG ID values! *** "
                    + e.getMessage());
            return getEmployee(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (!checker.checkAttribute(idCheck + "")
                    && !checker.checkAttribute(fioWithoutSpaces)
                    && !checker.checkAttribute(dbornWithoutSpaces)
                    && !checker.checkAttribute(workAreaWithoutSpaces)
                    && !checker.checkAttribute(roomWithoutSpaces)
            ) {
                Profession prof = Profession.Преподаватель;
                Profession[] professionsArray = Profession.values();

                for (Profession prf : professionsArray
                ) {
                    if (prf.getProfession().equals(profession)) {
                        prof = prf;
                        break;
                    }
                }

                Employee employee = new Employee(idCheck
                        , fioWithoutSpaces
                        , dbornWithoutSpaces
                        , prof
                        , workarea
                        , room);

                employeeService.updateEmployee(employee);

                return getEmployee(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            logger.error("*** EmployeeController.updateEmployee(): WRONG DB values! *** "
                    + e.getMessage());
            return getEmployee(model);
        }
    }


    @PostMapping("/findbyfio")
    public String findEmployeeByFio(@RequestParam String fio,
                                    Model model) {

        if (checker.checkAttribute(fio)
        ) {
            logger.warn("*** EmployeeController.findEmployeeByFio():" +
                    "  Attribute has a null value! ***");
            return getEmployee(model);
        }

        String fioWithoutSpaces = fio.trim();
        try {

            int idCheck = Integer.parseInt(fioWithoutSpaces);
            if (idCheck <= 0 || checker.checkAttribute(idCheck + "")) {
                logger.warn("*** EmployeeController.updateEmployee(): sub-zero ID values! ***");
                return getEmployee(model);
            } else {
                List<Employee> employeeList = employeeService.findEmployeeById(idCheck);
                model.addAttribute("employeeList", employeeList);
                logger.debug("*** EmployeeController.updateEmployee(): " +
                        "found an employee by ID ***");
                return "employee";
            }
        } catch (Exception e) {
            try {
                List<Employee> employeeList = employeeService.findEmployeeByFio(fioWithoutSpaces);
                model.addAttribute("employeeList", employeeList);
                logger.debug("EmployeeController.updateEmployee(): " +
                        "found an employee by fio  *** " + e.getMessage());
                return "employee";
            } catch (Exception e1) {
                logger.error("*** EmployeeController.updateEmployee(): WRONG DB values! *** "
                        + e1.getMessage());
                return getEmployee(model);
            }
        }
    }

    @PostMapping("/findemployeebyprofession")
    public String findEmployeesByCategory(@RequestParam String profession
            , Model model) {

        if (checker.checkAttribute(profession)) {
            logger.warn("*** EmployeeController.findEmployeesByCategory():" +
                    "  Attribute has a null value! ***");
            return getEmployee(model);
        }

        try {
            Profession prof = Profession.Преподаватель;
            Profession[] professionsArray = Profession.values();

            for (Profession prf : professionsArray
            ) {
                if (prf.getProfession().equals(profession)) {
                    prof = prf;
                    break;
                }
            }
            List<Employee> employeeList = employeeService.findEmployeeListByProfession(prof);
            model.addAttribute("employeeList", employeeList);

            return "employee";
        } catch (Exception e) {
            logger.error("*** EmployeeController.findEmployeesByCategory(): WRONG DB values! *** "
                    + e.getMessage());
            return getEmployee(model);
        }
    }
}
