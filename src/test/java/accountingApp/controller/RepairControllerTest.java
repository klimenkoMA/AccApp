package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.entity.Important;
import accountingApp.entity.Repair;
import accountingApp.service.DevicesService;
import accountingApp.service.RepairService;
import accountingApp.usefulmethods.Checker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRepair() {
    }

    @Test
    void addNewRepair() {
    }

    @Test
    void deleteRepair() {
    }

    @Test
    void findRepair() {
    }

    @Test
    void updateRepair() {
    }
}