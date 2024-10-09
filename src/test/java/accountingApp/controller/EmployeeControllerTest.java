package accountingApp.controller;


import accountingApp.entity.Employee;
import accountingApp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    EmployeeService service;


    private List<Employee> getEmployee() {

        Employee empl1 = new Employee(1, "A", "20051977", "УПЦ", "111");
        Employee empl2 = new Employee(2, "Б", "01071988", "ПОЛИГОН", "13");
        Employee empl3 = new Employee(3, "Ц", "02051966", "УПЦ", "202");

        List<Employee> employees = new ArrayList<>();
        employees.add(empl1);
        employees.add(empl2);
        employees.add(empl3);

        return employees;

    }

    @Test
    void mustReturnEmployeesList() throws Exception {

        Mockito.when(this.service.getListEmployee()).thenReturn(getEmployee());

        mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }




}
