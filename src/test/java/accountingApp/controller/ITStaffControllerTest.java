package accountingApp.controller;

import accountingApp.entity.ITStaff;
import accountingApp.entity.Profession;
import accountingApp.service.ITStaffService;
import accountingApp.usefulmethods.Checker;
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

import static org.mockito.Mockito.*;

class ITStaffControllerTest {

    @InjectMocks
    private ITStaffController itStaffController;
    @Mock
    private Model model;
    @Mock
    private ITStaffService itStaffService;
    @Mock
    private Checker checker;

    private final List<ITStaff> itStaffList;
    private Profession profession;

    {
        profession = Profession.Преподаватель;
        ITStaff i1 = new ITStaff();
        ITStaff i2 = new ITStaff();
        ITStaff i3 = new ITStaff();
        itStaffList = Arrays.asList(i1, i2, i3);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getItStaffShouldReturnITStaffList() {

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.getItStaff(model);

        Assertions.assertEquals("itstaff", viewName);

        verify(model).addAttribute("itStaffList", itStaffList);

        verify(itStaffService).getAllItStaff();

    }

    @Test
    void addItStaffValid() {

        String itStaffFio = "Mikl First";

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.addItStaff(itStaffFio
                , profession.getProfession()
                , model);

        Assertions.assertEquals("itstaff", viewName);

        verify(itStaffService).addNewItStaff(any());

    }

    @Test
    void addItStaffFail() {

        String itStaffFio = "4";

        ITStaff itStaff = new ITStaff(itStaffFio, profession);

        String viewName = itStaffController.addItStaff(itStaffFio
                , profession.getProfession()
                , model);

        Assertions.assertEquals("itstaff", viewName);

        verify(model, never()).addAttribute("itStaffList", itStaffList);

        verify(itStaffService, never()).addNewItStaff(itStaff);

    }

    @Test
    void addItStaffFailWithException() {

        String itStaffFio = "name";

        ITStaff itStaff = new ITStaff(itStaffFio, profession);

        doThrow(new RuntimeException()).when(itStaffService).addNewItStaff(any());

        verify(model, never()).addAttribute("itStaffList", itStaffList);

        verify(itStaffService, never()).addNewItStaff(itStaff);

    }

    @Test
    void deleteItStaffValid() {

        String itStaffId = "15";
        int idCheck = Integer.parseInt(itStaffId);

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.deleteITStaff(itStaffId, model);

        Assertions.assertEquals("itstaff", viewName);

        verify(itStaffService).deleteITStaffById(idCheck);

    }

    @Test
    void deleteItStaffFail() {

        String itStaffId = "-15";
        int idCheck = Integer.parseInt(itStaffId);

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.deleteITStaff(itStaffId, model);

        Assertions.assertEquals("itstaff", viewName);

        verify(itStaffService, never()).deleteITStaffById(idCheck);

    }

    @Test
    void deleteItStaffFailWithException() {

        String itStaffId = "15";
        int idCheck = Integer.parseInt(itStaffId);

        doThrow(new RuntimeException()).when(itStaffService).deleteITStaffById(idCheck);

        verify(itStaffService, never()).deleteITStaffById(idCheck);

    }

    @Test
    void updateItStaffValid() {

        String itStaffId = "15";
        String fio = "Mikl First";

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.updateItStaff(itStaffId
                , fio
                , profession.getProfession()
                , model);

        Assertions.assertEquals("itstaff", viewName);

        verify(itStaffService).updateItStaff(any());

    }

    @Test
    void updateItStaffFail() {

        String itStaffId = "-15";
        String fio = "Mikl First";

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.updateItStaff(itStaffId
                , fio
                , profession.getProfession()
                , model);

        Assertions.assertEquals("itstaff", viewName);

        verify(itStaffService, never()).updateItStaff(any());

    }

    @Test
    void updateItStaffFailWithException() {

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        doThrow(new RuntimeException()).when(itStaffService).updateItStaff(any());

        verify(itStaffService, never()).updateItStaff(any());

    }

    @Test
    void findItStaffValid() {

        String itStaffId = "15";
        int idCheck = Integer.parseInt(itStaffId);

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.findItStaffById(itStaffId, model);

        Assertions.assertEquals("itstaff", viewName);

        verify(itStaffService).getITStaffById(idCheck);
    }

    @Test
    void findItStaffFail() {

        String itStaffId = "-15";
        int idCheck = Integer.parseInt(itStaffId);

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        String viewName = itStaffController.findItStaffById(itStaffId, model);

        Assertions.assertEquals("itstaff", viewName);

        verify(itStaffService, never()).getITStaffById(idCheck);
    }

    @Test
    void findItStaffValidWithException() {

        String itStaffId = "15";
        int idCheck = Integer.parseInt(itStaffId);

        Mockito.when(itStaffService.getAllItStaff()).thenReturn(itStaffList);

        doThrow(new RuntimeException()).when(itStaffService).getITStaffById(idCheck);

        verify(itStaffService, never()).getITStaffById(idCheck);
    }
}