package accountingApp.controller;


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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustReturnEmployeesList() {

        Employee empl1 = new Employee(1, "A", "20051977", "БГУ", "111");
        Employee empl2 = new Employee(2, "Б", "01071988", "МГУ", "13");
        Employee empl3 = new Employee(3, "Ц", "02051966", "ФГУ", "202");

        List<Employee> employee = new ArrayList<>();

        employee.add(empl1);
        employee.add(empl2);
        employee.add(empl3);

        Mockito.when(this.employeeService.getListEmployee()).thenReturn(employee);

        when(employeeService.getListEmployee()).thenReturn(employee);

        String viewName = employeeController.getEmployee(model);

        Assertions.assertEquals("employee", viewName);

        verify(model).addAttribute("employeeList", employee);

        verify(employeeService).getListEmployee();
    }


}
