package accountingApp.controller;


import accountingApp.entity.Employee;
import accountingApp.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest {

    @Autowired
    MockMvc mvc;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    EmployeeService service;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustReturnEmployeesList() throws Exception {

        Employee empl1 = new Employee(1, "A", "20051977", "УПЦ", "111");
        Employee empl2 = new Employee(2, "Б", "01071988", "ПОЛИГОН", "13");
        Employee empl3 = new Employee(3, "Ц", "02051966", "УПЦ", "202");

        List<Employee> employees = new ArrayList<>();

        employees.add(empl1);
        employees.add(empl2);
        employees.add(empl3);

        Mockito.when(this.service.getListEmployee()).thenReturn(employees);

        mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }


}
