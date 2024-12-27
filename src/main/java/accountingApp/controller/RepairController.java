package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.entity.Repair;
import accountingApp.service.DevicesService;
import accountingApp.service.RepairService;
import accountingApp.usefulmethods.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RepairController {

    private final Logger logger = LoggerFactory.getLogger(RepairController.class);

    @Autowired
    private RepairService repairService;
    @Autowired
    private DevicesService devicesService;
    @Autowired
    private Checker checker;

    @GetMapping("/repair")
    public String getRepair(Model model){
        List<Repair> repairList = repairService.getAllRepairs();
        List<Devices> devicesList = devicesService.findAllDevices();

        model.addAttribute("repairList", repairList);
        model.addAttribute("devicesList", devicesList);
        return "repair";
    }
}
