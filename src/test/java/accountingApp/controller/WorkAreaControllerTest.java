package accountingApp.controller;

import accountingApp.entity.WorkArea;
import accountingApp.service.WorkAreaService;
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

import static org.mockito.Mockito.verify;

class WorkAreaControllerTest {


    @InjectMocks
    WorkAreaController workAreaController;

    @Mock
    WorkAreaService workAreaService;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWorkAreaShouldReturnWorkAreaList() {
        WorkArea w1 = new WorkArea();
        WorkArea w2 = new WorkArea();
        WorkArea w3 = new WorkArea();
        WorkArea w4 = new WorkArea();
        List<WorkArea> workAreaList = Arrays.asList(w1, w2, w3, w4);

        Mockito.when(workAreaService.findAllWorkArea()).thenReturn(workAreaList);

        String viewName = workAreaController.getWorkArea(model);

        Assertions.assertEquals("workarea", viewName);

        verify(model).addAttribute("workAreaList", workAreaList);

        verify(workAreaService).findAllWorkArea();
    }

    @Test
    void addWorkArea() {
    }

    @Test
    void deleteWorkArea() {
    }

    @Test
    void updateWorkArea() {
    }

    @Test
    void findAreaByName() {
    }
}