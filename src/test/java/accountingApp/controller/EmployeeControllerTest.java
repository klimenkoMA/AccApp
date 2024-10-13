package accountingApp.controller;


import accountingApp.entity.Devices;
import accountingApp.entity.Employee;
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
        Employee empl1 = new Employee(1, "A", "20051977", "БГУ", "111");
        Employee empl2 = new Employee(2, "Б", "01071988", "МГУ", "13");
        Employee empl3 = new Employee(3, "Ц", "02051966", "ФГУ", "202");

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

        Mockito.when(this.employeeService.getListEmployee()).thenReturn(employeeList);

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

        String viewName = employeeController.addEmployee(employeeFio, employeeDborn, employeeWorkArea,
                employeeRoom, model);

        when(employeeService.getListEmployee()).thenReturn(new ArrayList<>());

        Assertions.assertEquals("employee", viewName);

        verify(employeeService).addNewEmployee(any(Employee.class));

    }

    @Test
    void deleteById() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void findEmployeeByFio() {
    }
}
