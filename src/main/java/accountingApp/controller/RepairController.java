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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getRepair(Model model) {
        List<Repair> repairList = repairService.getAllRepairs();
        List<Devices> devicesList = devicesService.findAllDevices();

        model.addAttribute("repairList", repairList);
        model.addAttribute("devicesList", devicesList);
        return "repair";
    }

    @PostMapping("/addrepair")
    public String addNewRepair(@RequestParam String firstDay
            , @RequestParam(required = false) Devices device
            , Model model) {
        if (checker.checkAttribute(firstDay)
                || device == null) {
            logger.warn("*** RepairController.addNewRepair():  Attribute has a null value! ***");
            return getRepair(model);
        }
        try {
            Repair repair = new Repair(firstDay, device);
            repairService.createRepair(repair);
            return getRepair(model);
        } catch (Exception e) {
            logger.error("*** RepairController.addNewRepair():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRepair(model);
        }
    }

    @PostMapping("/deleterepair")
    public String deleteRepair(@RequestParam String id
            , Model model) {
        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** RepairController.deleteRepair():  Attribute has a null value! ***");
            return getRepair(model);
        }
        String idClear = id.trim();
        try {
            long idCheck = Long.parseLong(idClear);
            if (idCheck <= 0) {
                logger.warn("*** RepairController.deleteRepair():  ID is SUBZERO! ***");
            } else {
                List<Repair> rList = repairService.findRepair(idCheck);
                rList.get(0).setDevice(new Devices());
                repairService.updateRepair(rList.get(0));
                repairService.deleteRepair(idCheck);
            }
            return getRepair(model);
        } catch (Exception e) {
            logger.error("*** RepairController.deleteRepair():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRepair(model);
        }
    }

}
