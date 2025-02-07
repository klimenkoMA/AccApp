package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.entity.Important;
import accountingApp.entity.Repair;
import accountingApp.service.DevicesService;
import accountingApp.service.RepairService;
import accountingApp.usefulmethods.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class RepairControllerTest {

    @InjectMocks
    private RepairController repairController;
    @Mock
    private DevicesService devicesService;
    @Mock
    private RepairService repairService;
    @Mock
    private Model model;
    @Mock
    private Checker checker;


    private List<Repair> repairList;
    private Repair repair;
    private Long id;
    private String firstDay;
    private String lastRepairDay;
    private int repairCount;
    private boolean isImportant;
    private Devices device;
    private String health;
    private int durability;
    private String repairedPart;
    private List<String> repairedParts;
    private List<Important> importants;
    private String viewName;

    {
        repairList = new ArrayList<>();
        repair = new Repair();
        id = 111L;
        firstDay = "08-05-2024";
        lastRepairDay = "15-01-2025";
        repairCount = 3;
        isImportant = true;
        device = new Devices();
        health = "green";
        durability = 35;
        repairedPart = "трансформатор";
        repairedParts = new ArrayList<>();
        importants = new ArrayList<>();
        repairList.add(repair);
        repairList.add(repair);
        repairList.add(repair);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getValidRepair() {
        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.getRepair(model);

        Assertions.assertEquals("repair", viewName);

        verify(model).addAttribute("repairList", repairList);

        verify(repairService).getAllRepairs();

    }

    @Test
    void addValidNewRepair() {

        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.addNewRepair(firstDay
                , device
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, atMost(1))
                .createRepair(repair);
    }

    @Test
    void addEmptyAttrNewRepair() {

        firstDay = " ";
        device = null;

        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.addNewRepair(firstDay
                , device
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, never())
                .createRepair(repair);
    }

    @Test
    void addNewRepairWithException() {

        when(repairService.getAllRepairs()).thenReturn(repairList);

        doThrow(new RuntimeException()).when(repairService).createRepair(repair);

        verify(repairService, never())
                .createRepair(repair);
    }

    @Test
    void deleteValidRepair() {
        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.addNewRepair(firstDay
                , device
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, atMost(1))
                .deleteRepair(1);

    }

    @Test
    void deleteFailRepair() {

        id = -1L;
        device = null;

        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.addNewRepair(firstDay
                , device
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, never())
                .deleteRepair(id);

    }

    @Test
    void deleteRepairWithException() {

        doThrow(new RuntimeException()).when(repairService).createRepair(repair);

        verify(repairService, never())
                .deleteRepair(1);
    }


    @Test
    void findRepairSuccess() {
        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.addNewRepair(firstDay
                , device
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, (atMost(1)))
                .findRepair(1);
    }

    @Test
    void findRepairFail() {
        id = -1L;
        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.addNewRepair(firstDay
                , device
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, never()).findRepair(id);
    }

    @Test
    void findRepairWithException() {

        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.addNewRepair(firstDay
                , device
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, never()).findRepair(id);
    }

    @Test
    void updateRepairValid() {
        id = 1L;
        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.updateRepair(id + ""
                , lastRepairDay
                , isImportant + ""
                , repairedPart
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, atMost(1))
                .updateRepair(repair);
    }

    @Test
    void updateRepairFail() {
        id = -1L;
        when(repairService.getAllRepairs()).thenReturn(repairList);

        viewName = repairController.updateRepair(id + ""
                , lastRepairDay
                , isImportant + ""
                , repairedPart
                , model);

        Assertions.assertEquals("repair", viewName);

        verify(repairService, never())
                .updateRepair(repair);
    }

    @Test
    void updateRepairWithException() {
        id = 1L;
        when(repairService.getAllRepairs()).thenReturn(repairList);

        doThrow(new RuntimeException()).when(repairService).createRepair(repair);

        verify(repairService, never())
                .updateRepair(repair);
    }

}