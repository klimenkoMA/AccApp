package accountingApp.controller;


import accountingApp.entity.Employee;
import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    private Model model;

    private final List<Employee> employeeList;

    {
        Employee empl1 = new Employee(1, "A", "20051977"
                , new WorkArea("БГУ"), new Room("111", new WorkArea()));
        Employee empl2 = new Employee(2, "Б", "01071988"
                , new WorkArea("МГУ"), new Room("13", new WorkArea()));
        Employee empl3 = new Employee(3, "Ц", "02051966"
                , new WorkArea("ФГУ"), new Room("202", new WorkArea()));

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

        try {
            String viewName = employeeController.getEmployee(model);
            Assertions.assertEquals("employee", viewName);
            verify(model).addAttribute("employeeList", employeeList);
        } catch (Exception e) {
            e.getMessage();
        }

        verify(employeeService).getListEmployee();
    }

    @Test
    void addEmployeeValidAttributesOfEmployeeAdded() {

        String employeeFio = "TestName";
        String employeeDborn = "11051988";
        String employeeWorkArea = "МГУ";
        String employeeRoom = "115";

        try {
            String viewName = employeeController.addEmployee(employeeFio, employeeDborn
                    , new WorkArea(employeeWorkArea), new Room(employeeRoom, new WorkArea()), model);
            Assertions.assertEquals("employee", viewName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        when(employeeService.getListEmployee()).thenReturn(new ArrayList<>());


    }

    @Test
    void addEmployeeEmptyAttributesOfEmployeeAdded() {

        String employeeFio = " ";
        String employeeDborn = " ";
        String employeeWorkArea = " ";
        String employeeRoom = " ";


        try {
            when(employeeController.addEmployee(employeeFio, employeeDborn
                    , new WorkArea(employeeWorkArea), new Room(employeeRoom, new WorkArea(employeeWorkArea)), model))
                    .thenReturn("employeeList");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        when(employeeService.getListEmployee()).thenReturn(new ArrayList<>());

        verify(employeeService, never()).addNewEmployee(any(Employee.class));

    }

    @Test
    void deleteEmployeeDeletedEmployeeSuccess() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);

        when(this.employeeService.getListEmployee()).thenReturn(Arrays.asList(new Employee(),
                new Employee()));

        try {
            String result = employeeController.deleteById(employeeId, model);
            Assertions.assertEquals("employee", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        verify(employeeService).deleteEmployeeById(idCheck);


    }

    @Test
    void deleteEmployeeDeletedEmployeeFail() {

        String employeeId = "0";
        int idCheck = Integer.parseInt(employeeId);

        try {
            String result = employeeController.deleteById(employeeId, model);
            Assertions.assertEquals("employee", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        verify(employeeService, never()).deleteEmployeeById(idCheck);


    }

    @Test
    void deleteEmployeeDeletedNoNumericId() {

        String employeeId = "abc";

        try {
            String result = employeeController.deleteById(employeeId, model);
            Assertions.assertEquals("employee", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
                , employeeFio, employeeDborn, new WorkArea(employeeWorkArea),
                new Room(employeeRoom, new WorkArea())));

        when(employeeService.getListEmployee()).thenReturn(employees);
        try {
            String result = employeeController.updateEmployee(employeeId, employeeFio, employeeDborn
                    , new WorkArea(employeeWorkArea), new Room(employeeRoom, new WorkArea(employeeWorkArea)), model);
            Assertions.assertEquals("employee", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Test
    void updateEmployeeFail() {

        String employeeId = "-1";
        int idCheck = Integer.parseInt(employeeId);
        String employeeFio = "TestName";
        String employeeDborn = "11051988";
        String employeeWorkArea = "МГУ";
        String employeeRoom = "115";

        List<Employee> employees = Collections.singletonList(new Employee(idCheck
                , employeeFio, employeeDborn, new WorkArea(employeeWorkArea),
                new Room(employeeRoom, new WorkArea())));

        when(employeeService.getListEmployee()).thenReturn(employees);

        try {
            String result = this.employeeController.updateEmployee(employeeId, employeeFio, employeeDborn
                    , new WorkArea(employeeWorkArea), new Room(employeeRoom, new WorkArea(employeeWorkArea)), model);
            Assertions.assertEquals("employee", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        verify(employeeService, never()).updateEmployee(any(Employee.class));
    }

    @Test
    void updateEmployeeFailWithEmptyAttributes() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);
        String employeeFio = " ";
        String employeeDborn = " ";
        String employeeWorkArea = " ";
        String employeeRoom = " ";

        List<Employee> employees = Collections.singletonList(new Employee(idCheck
                , employeeFio, employeeDborn, new WorkArea(employeeWorkArea),
                new Room(employeeRoom, new WorkArea())));

        when(employeeService.getListEmployee()).thenReturn(employees);

        try {
            String result = this.employeeController.updateEmployee(employeeId, employeeFio, employeeDborn
                    , new WorkArea(employeeWorkArea), new Room(employeeRoom, new WorkArea(employeeWorkArea)), model);
            Assertions.assertEquals("employee", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        verify(employeeService, never()).updateEmployee(new Employee(idCheck
                , employeeFio, employeeDborn, new WorkArea(employeeWorkArea),
                new Room(employeeRoom, new WorkArea())));
    }

    @Test
    void updateEmployeeFailWithException() {

        String employeeId = "1";
        int idCheck = Integer.parseInt(employeeId);
        String employeeFio = " ";
        String employeeDborn = " ";
        String employeeWorkArea = " ";
        String employeeRoom = " ";

        Employee employee = new Employee(idCheck
                , employeeFio, employeeDborn, new WorkArea(employeeWorkArea),
                new Room(employeeRoom, new WorkArea()));

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
