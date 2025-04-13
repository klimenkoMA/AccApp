package accountingApp.controller;

import accountingApp.entity.Employee;
import accountingApp.entity.Profession;
import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.entity.dto.employeedto.MaxEmployeesInWorkAreaDTO;
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        List<String> professionList = Arrays.stream(Profession.values())
                .map(Profession::getProfession)
                .collect(Collectors.toList());

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

                Profession prof = Arrays.stream(Profession.values())
                        .filter(prf -> prf.getProfession().equals(profession))
                        .findFirst()
                        .orElse(Profession.Преподаватель);

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

                Profession prof = Arrays.stream(Profession.values())
                        .filter(prf -> prf.getProfession().equals(profession))
                        .findFirst()
                        .orElse(Profession.Преподаватель);

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
            Profession prof = Arrays.stream(Profession.values())
                    .filter(prf -> prf.getProfession().equals(profession))
                    .findFirst()
                    .orElse(Profession.Преподаватель);

            List<Employee> employeeList = employeeService.findEmployeeListByProfession(prof);
            model.addAttribute("employeeList", employeeList);

            return "employee";
        } catch (Exception e) {
            logger.error("*** EmployeeController.findEmployeesByCategory(): WRONG DB values! *** "
                    + e.getMessage());
            return getEmployee(model);
        }
    }

    @PostMapping("/findemployeebyattrs")
    public String findEmployeesByAttrs(@RequestParam String attrs
            , Model model) {

        if (checker.checkAttribute(attrs)) {
            logger.warn("*** EmployeeController.findEmployeesByAttrs():" +
                    "  Attribute has a null value! ***");
            return getEmployee(model);
        }

        String attrsWithoutSpaces = attrs.trim().toLowerCase(Locale.ROOT);
        try {

            List<Employee> employees = employeeService.getListEmployee();
            List<Employee> employeeList = new ArrayList<>();

            for (Employee emp : employees
            ) {
                if ((emp.getId() + "").contains(attrsWithoutSpaces)) {
                    employeeList.add(emp);
                } else if (emp.getFio().toLowerCase(Locale.ROOT).toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    employeeList.add(emp);
                } else if (emp.getProfession().name().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    employeeList.add(emp);
                } else if (emp.getWorkarea().getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    employeeList.add(emp);
                } else if (emp.getDborn().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    employeeList.add(emp);
                } else if (emp.getRoom().getNumber().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    employeeList.add(emp);
                }
            }

            model.addAttribute("employeeList", employeeList);

            if (employeeList.isEmpty()) {
                logger.debug("*** EmployeeController.findEmployeesByAttrs():  DATA NOT FOUND IN DB***");
                return getEmployee(model);
            }

            return "employee";
        } catch (Exception e) {
            logger.error("*** EmployeeController.findEmployeesByAttrs(): WRONG DB values! *** "
                    + e.getMessage());
            return getEmployee(model);
        }
    }

    @GetMapping("/maxemployeescountreport")
    public String maxEmployeesCountReport(Model model) {

        List<MaxEmployeesInWorkAreaDTO> dtoList = employeeService.getEmployeesCount();

        dtoList = dtoList.stream()
                .sorted(Comparator
                        .comparingLong(MaxEmployeesInWorkAreaDTO::getEmployeesCount)
                        .reversed())
                .collect(Collectors.toList());

        long[] counts = dtoList.stream()
                .mapToLong(MaxEmployeesInWorkAreaDTO::getEmployeesCount)
                .toArray();

        String[] workAreas = dtoList.stream()
                .map(MaxEmployeesInWorkAreaDTO::getWorkAreaName)
                .toArray(String[]::new);

        String beginColor = "rgb(47,";
        int secondPartColor = 125;
        int thirdPartColor = 225;

        String[] colors = IntStream.range(0, dtoList.size())
                .mapToObj(j -> beginColor +
                        (secondPartColor - 15 - (j * 10)) + "," +
                        (thirdPartColor - (j * 10)) + ")")
                .toArray(String[]::new);

        model.addAttribute("dtoList", dtoList);
        model.addAttribute("workAreas", workAreas);
        model.addAttribute("counts", counts);
        model.addAttribute("colors", colors);


        return "/reports/workareareports/reportemployeescountinworkareas";
    }
}
