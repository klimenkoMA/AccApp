package accountingApp.controller;

import accountingApp.entity.ITStaff;
import accountingApp.service.ITStaffService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class ITStaffControllerTest {

    @InjectMocks
    private ITStaffController itStaffController;

    @Mock
    private Model model;

    @Mock
    private ITStaffService itStaffService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getItStaffShouldReturnITStaffList() {
        ITStaff i1 = new ITStaff();
        ITStaff i2 = new ITStaff();
        ITStaff i3 = new ITStaff();
        List<ITStaff> itStaffList = Arrays.asList(i1,i2,i3);

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.getItStaff(model);

        Assertions.assertEquals("itstaff", viewName);

        verify(model).addAttribute("itStaffList", itStaffList);

        verify(itStaffService).getAllItStaff();

    }

    @Test
    void addItStaff() {
    }

    @Test
    void deleteITStaff() {
    }

    @Test
    void updateItStaff() {
    }

    @Test
    void findPersonById() {
    }
}