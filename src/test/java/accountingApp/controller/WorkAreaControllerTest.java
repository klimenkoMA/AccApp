package accountingApp.controller;

import accountingApp.entity.WorkArea;
import accountingApp.service.WorkAreaService;
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

class WorkAreaControllerTest {


    @InjectMocks
    private WorkAreaController workAreaController;
    @Mock
    private WorkAreaService workAreaService;
    @Mock
    private Model model;
    @Mock
    private Checker checker;

    private final List<WorkArea> workAreaList;
    private String description;

    {
        description = "description";
        WorkArea w1 = new WorkArea();
        WorkArea w2 = new WorkArea();
        WorkArea w3 = new WorkArea();
        WorkArea w4 = new WorkArea();
        workAreaList = Arrays.asList(w1, w2, w3, w4);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWorkAreaShouldReturnWorkAreaList() {

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.getWorkArea(model);

        Assertions.assertEquals("workarea", viewName);

        verify(model).addAttribute("workAreaList", workAreaList);

        verify(workAreaService).findAllWorkArea();
    }

    @Test
    void addWorkAreaValid() {
        String nameWA = "BGU";

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.addWorkArea(nameWA, description, model);

        Assertions.assertEquals("workarea", viewName);

    }

    @Test
    void addWorkAreaFail() {
        String nameWA = " ";

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.addWorkArea(nameWA, description, model);

        Assertions.assertEquals("workarea", viewName);

        verify(model, times(1)).addAttribute("workAreaList", workAreaList);

        verify(workAreaService, never()).addNewWorkArea(new WorkArea(nameWA, description));
    }

    @Test
    void addWorkAreaFailWithException() {
        String nameWA = "fdf";
        String roomId = "12000";

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        doThrow(new RuntimeException()).when(workAreaService).addNewWorkArea(any());

        verify(model, never()).addAttribute("workAreaList", workAreaList);

        verify(workAreaService, never()).addNewWorkArea(any());
    }

    @Test
    void deleteWorkAreaValid() {
        String id = "15";
        int idCheck = Integer.parseInt(id);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.deleteWorkArea(id, model);

        Assertions.assertEquals("workarea", viewName);

        verify(model, times(1)).addAttribute("workAreaList", workAreaList);

        verify(workAreaService, times(1)).deleteWorkAreaById(idCheck);
    }

    @Test
    void deleteWorkAreaFail() {
        String id = "-15";
        int idCheck = Integer.parseInt(id);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.deleteWorkArea(id, model);

        Assertions.assertEquals("workarea", viewName);

        verify(model).addAttribute("workAreaList", workAreaList);

        verify(workAreaService, never()).deleteWorkAreaById(idCheck);
    }

    @Test
    void deleteWorkAreaFailWithException() {
        String id = "15";
        int idCheck = Integer.parseInt(id);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        doThrow(new RuntimeException()).when(workAreaService).deleteWorkAreaById(idCheck);

        verify(model, never()).addAttribute("workAreaList", workAreaList);

        verify(workAreaService, never()).deleteWorkAreaById(idCheck);
    }

    @Test
    void updateWorkAreaValid() {
        String id = "15";
        String workAreaTitle = "GTU";

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.updateWorkArea(id, workAreaTitle
                , description, model);

        Assertions.assertEquals("workarea", viewName);

        verify(model, times(1)).addAttribute("workAreaList", workAreaList);

        verify(workAreaService, times(1)).updateWorkArea(any());
    }

    @Test
    void updateWorkAreaFail() {
        String id = "-15";
        String workAreaTitle = "GTU";
        int idCheck = Integer.parseInt(id);

        WorkArea workArea = new WorkArea(idCheck, workAreaTitle, description);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.updateWorkArea(id, workAreaTitle
                , description, model);

        Assertions.assertEquals("workarea", viewName);

        verify(model, times(1)).addAttribute("workAreaList", workAreaList);

        verify(workAreaService, never()).updateWorkArea(workArea);
    }

    @Test
    void updateWorkAreaFailWithException() {
        String id = "15";
        String workAreaTitle = "GTU";

        WorkArea workArea = new WorkArea(id, description);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        doThrow(new RuntimeException()).when(workAreaService).updateWorkArea(workArea);

        verify(workAreaService, never()).updateWorkArea(workArea);
    }

    @Test
    void findWorkAreaValid() {
        String workAreaTitle = "15";
        int idCheck = Integer.parseInt(workAreaTitle);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.findAreaByName(workAreaTitle, model);

        Assertions.assertEquals("workarea", viewName);

        verify(workAreaService, times(1)).getWorkAreaById(idCheck);
    }

    @Test
    void findWorkAreaFail() {
        String workAreaTitle = "-15";
        int idCheck = Integer.parseInt(workAreaTitle);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.findAreaByName(workAreaTitle, model);

        Assertions.assertEquals("workarea", viewName);

        verify(workAreaService, never()).getWorkAreaById(idCheck);
    }

    @Test
    void findWorkAreaFailWithException() {
        String workAreaTitle = "15";
        int idCheck = Integer.parseInt(workAreaTitle);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        doThrow(new RuntimeException()).when(workAreaService).getWorkAreaById(idCheck);

        verify(workAreaService, never()).getWorkAreaById(idCheck);
    }
}