package accountingApp.controller;


import accountingApp.entity.Employee;
import accountingApp.entity.Profession;
import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.EmployeeService;
import accountingApp.service.RoomService;
import accountingApp.service.WorkAreaService;
import accountingApp.usefulmethods.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private RoomService roomService;
    @Mock
    private WorkAreaService workAreaService;
    @Mock
    private Checker checker;
    @Mock
    private Model model;

    private final List<Employee> employeeList;
    private String description;
    private Profession profession;

    {
        description = "description";
        profession = Profession.Преподаватель;
        Employee empl1 = new Employee(1
                , "A"
                , "20051977"
                , profession
                , new WorkArea("БГУ"
                , description)
                , new Room("111"
                , new WorkArea()
                , description));
        Employee empl2 = new Employee(2
                , "Б"
                , "01071988"
                , profession
                , new WorkArea("МГУ"
                , description)
                , new Room("13"
                , new WorkArea()
                , description));
        Employee empl3 = new Employee(3
                , "Ц"
                , "02051966"
                , profession
                , new WorkArea("ФГУ"
                , description)
                , new Room("202"
                , new WorkArea()
                , description));

        employeeList = new ArrayList<>();

        employeeList.add(empl1);
        employeeList.add(empl2);
        employeeList.add(empl3);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustReturnEmployeesList() {

        Mockito.when(employeeService.getListEmployee()).thenReturn(employeeList);

        when(employeeService.getListEmployee()).thenReturn(employeeList);


        String viewName = employeeController.getEmployee(model);
        Assertions.assertEquals("employee", viewName);
        verify(model).addAttribute("employeeList", employeeList);


        verify(employeeService).getListEmployee();
    }

    @Test
    void addEmployeeValidAttributesOfEmployeeAdded() {

        String employeeFio = "TestName";
        String employeeDborn = "11051988";
        String employeeWorkArea = "МГУ";
        String employeeRoom = "115";

        String viewName = employeeController.addEmployee(employeeFio
                , employeeDborn
                , profession.getProfession()
                , new WorkArea(employeeWorkArea
                        , description)
                , new Room(employeeRoom
                        , new WorkArea()
                        , description)
                , model);
        Assertions.assertEquals("employee", viewName);


        when(employeeService.getListEmployee()).thenReturn(new ArrayList<>());
    }

    @Test
    void addEmployeeEmptyAttributesOfEmployeeAdded() {

        String employeeFio = " ";
        String employeeDborn = " ";
        String employeeWorkArea = " ";
        String employeeRoom = " ";
        description = " ";

        Employee employee = new Employee(employeeFio
                , employeeDborn
                , profession
                , new WorkArea(employeeWorkArea
                , description)
                , new Room(employeeRoom
                , new WorkArea()
                , description));

        when(employeeController.addEmployee(employeeFio
                , employeeDborn
                , profession.getProfession()
                , new WorkArea(employeeWorkArea
                        , description)
                , new Room(employeeRoom
                        , new WorkArea(employeeWorkArea
                        , description)
                        , description)
                , model)
        ).thenThrow(new RuntimeException());

        when(employeeService.getListEmployee()).thenReturn(new ArrayList<>());

        verify(employeeService, never()).addNewEmployee(employee);
    }

    @Test
    void deleteEmployeeDeletedEmployeeSuccess() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);

        when(this.employeeService.getListEmployee()).thenReturn(Arrays.asList(new Employee(),
                new Employee()));

        String result = employeeController.deleteById(employeeId, model);
        Assertions.assertEquals("employee", result);

        verify(employeeService).deleteEmployeeById(idCheck);


    }

    @Test
    void deleteEmployeeDeletedEmployeeFail() {

        String employeeId = "0";
        int idCheck = Integer.parseInt(employeeId);

        String result = employeeController.deleteById(employeeId, model);
        Assertions.assertEquals("employee", result);

        verify(employeeService, never()).deleteEmployeeById(idCheck);


    }

    @Test
    void deleteEmployeeDeletedNoNumericId() {

        String employeeId = "abc";

        String result = employeeController.deleteById(employeeId, model);
        Assertions.assertEquals("employee", result);

        verify(employeeService, never()).deleteEmployeeById(anyInt());


    }

    @Test
    void deleteEmployeeWithException() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);

        doThrow(new RuntimeException()).when(employeeService).deleteEmployeeById(idCheck);

        verify(employeeService, never()).deleteEmployeeById(anyInt());
    }

    @Test
    void updateEmployeeValid() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);
        String employeeFio = "TestName";
        String employeeDborn = "11051988";
        String employeeWorkArea = "МГУ";
        String employeeRoom = "115";

        List<Employee> employees = Collections.singletonList(new Employee(idCheck
                , employeeFio
                , employeeDborn
                , profession
                , new WorkArea(employeeWorkArea
                , description)
                , new Room(employeeRoom
                , new WorkArea()
                , description)));

        when(employeeService.getListEmployee()).thenReturn(employees);

        String result = employeeController.updateEmployee(employeeId
                , employeeFio
                , employeeDborn
                , profession.getProfession()
                , new WorkArea(employeeWorkArea
                        , description)
                , new Room(employeeRoom
                        , new WorkArea(employeeWorkArea
                        , description)
                        , description)
                , model);
        Assertions.assertEquals("employee", result);
    }

    @Test
    void updateEmployeeFail() {

        when(employeeService.getListEmployee()).thenThrow(new RuntimeException());

        verify(employeeService, never()).updateEmployee(any(Employee.class));
    }

    @Test
    void updateEmployeeFailWithEmptyAttributes() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);
        String employeeFio = " ";
        String employeeDborn = " ";
        String employeeWorkArea = " ";
        String employeeRoom = " ";
        description = " ";

        when(employeeService.getListEmployee()).thenThrow(new RuntimeException());

        verify(employeeService, never()).updateEmployee(new Employee(idCheck
                , employeeFio
                , employeeDborn
                , profession
                , new WorkArea(employeeWorkArea, description),
                new Room(employeeRoom
                        , new WorkArea(), description)));
    }

    @Test
    void updateEmployeeFailWithException() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);
        String employeeFio = " ";
        String employeeDborn = " ";
        String employeeWorkArea = " ";
        String employeeRoom = " ";
        description = " ";

        Employee employee = new Employee(idCheck
                , employeeFio
                , employeeDborn
                , profession
                , new WorkArea(employeeWorkArea, description)
                , new Room(employeeRoom, new WorkArea(), description));

        doThrow(new RuntimeException()).when(employeeService).updateEmployee(employee);

        verify(employeeService, never()).updateEmployee(any(Employee.class));
    }

    @Test
    void findEmployeeValid() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);

        List<Employee> employees = Collections.singletonList(new Employee());

        when(employeeService.findEmployeeById(idCheck)).thenReturn(employees);

        String result = this.employeeController.findEmployeeByFio(employeeId, model);

        Assertions.assertEquals("employee", result);

        verify(model).addAttribute("employeeList", employees);

        verify(employeeService).findEmployeeById(idCheck);
    }

    @Test
    void findEmployeeInvalidBySubZeroId() {

        String employeeId = "-100";
        int idCheck = Integer.parseInt(employeeId);

        List<Employee> employees = Collections.singletonList(new Employee());

        when(employeeService.findEmployeeById(idCheck)).thenReturn(employees);

        String result = this.employeeController.findEmployeeByFio(employeeId, model);

        Assertions.assertEquals("employee", result);

        verify(model, never()).addAttribute("employeeList", employees);

        verify(employeeService, never()).findEmployeeById(idCheck);
    }

    @Test
    void findEmployeeThrowsException() {

        String employeeId = "100";
        int idCheck = Integer.parseInt(employeeId);

        List<Employee> employees = Collections.singletonList(new Employee());

        doThrow(new RuntimeException()).when(employeeService).findEmployeeById(idCheck);

        verify(model, never()).addAttribute("employeeList", employees);

        verify(employeeService, never()).findEmployeeById(idCheck);
    }
}
