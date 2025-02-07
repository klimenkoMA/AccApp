package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.entity.Important;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        List<String> importantList = new ArrayList<>();
        importantList.add("Высокая");
        importantList.add("Низкая");

        model.addAttribute("repairList", repairList);
        model.addAttribute("devicesList", devicesList);
        model.addAttribute("importantList", importantList);
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
                repairService.deleteRepair(idCheck);
            }
            return getRepair(model);
        } catch (Exception e) {
            logger.error("*** RepairController.deleteRepair():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRepair(model);
        }
    }

    @PostMapping("/findrepair")
    public String findRepair(@RequestParam String id
            , Model model) {
        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** RepairController.findRepair():  Attribute has a null value! ***");
            return getRepair(model);
        }
        String idClear = id.trim();
        try {
            long idCheck = Long.parseLong(idClear);
            if (idCheck <= 0) {
                logger.warn("*** RepairController.findRepair():  ID is SUBZERO! ***");
            } else {
                List<Repair> repairList = repairService.findRepair(idCheck);
                model.addAttribute("repairList", repairList);
                return "repair";
            }
            return getRepair(model);
        } catch (Exception e) {
            logger.error("*** RepairController.findRepair():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRepair(model);
        }
    }

    @PostMapping("/updaterepair")
    public String updateRepair(@RequestParam String id
            , @RequestParam String lastRepairDay
            , @RequestParam String isImportant
            , @RequestParam String repairedPart
            , Model model) {
        if (checker.checkAttribute(id)
                || checker.checkAttribute(lastRepairDay)
                || checker.checkAttribute(isImportant)
                || checker.checkAttribute(repairedPart)
        ) {
            logger.warn("*** RepairController.updateRepair():  Attribute has a null value! ***");
            return getRepair(model);
        }
        String idClear = id.trim();
        String lastRepairDayClear = lastRepairDay.trim();
        String isImportantClear = isImportant.trim();
        String repairedPartClear = repairedPart.trim();
        try {
            long idCheck = Long.parseLong(idClear);
            if (idCheck <= 0) {
                logger.warn("*** RepairController.updateRepair():  ID is SUBZERO! ***");
            } else {
                List<Repair> repairList = repairService.findRepair(idCheck);

                Repair currentRepair = repairList.get(0);
                Devices device = currentRepair.getDevice();
                String firstDay = currentRepair.getFirstDay();
                int repairCount = currentRepair.getRepairCount();
                String health = currentRepair.getHealth();
                int durability = currentRepair.getDurability();
                List<String> repairedParts = currentRepair.getRepairedParts();
                List<Important> importants = currentRepair.getImportants();

                boolean isImportantBoolean;
                isImportantBoolean = isImportantClear.equals("Высокая");
                Repair repair = new Repair(idCheck
                        , firstDay
                        , lastRepairDayClear
                        , repairCount
                        , isImportantBoolean
                        , device
                        , health
                        , durability
                        , repairedPartClear
                        , repairedParts
                        , importants);
                repairService.updateRepair(repair);
                device.setRepair(repair);
                devicesService.updateDevice(device);
            }
            return getRepair(model);
        } catch (Exception e) {
            logger.error("*** RepairController.updateRepair():  WRONG DB VALUES*** ");
            e.printStackTrace();
            return getRepair(model);
        }
    }

    @PostMapping("/findrepairbyattrs")
    public String findRepairByAttrs(@RequestParam String attrs
            , Model model) {
        if (checker.checkAttribute(attrs)
        ) {
            logger.warn("*** RepairController.findRepairByAttrs():  Attribute has a null value! ***");
            return getRepair(model);
        }
        String attrsClear = attrs.trim().toLowerCase(Locale.ROOT);
        try {
            List<Repair> repairs = repairService.getAllRepairs();
            List<Repair> repairList = new ArrayList<>();

            for (Repair rep : repairs
            ) {
                if ((rep.getId() + "").contains(attrsClear)) {
                    repairList.add(rep);
                } else if ((rep.getDurability() + "").contains(attrsClear)) {
                    repairList.add(rep);
                } else if (rep.getFirstDay().toLowerCase(Locale.ROOT)
                        .contains(attrsClear)) {
                    repairList.add(rep);
                } else if (rep.getLastRepairDay().toLowerCase(Locale.ROOT)
                        .contains(attrsClear)) {
                    repairList.add(rep);
                } else if ((rep.getRepairCount() + "").contains(attrsClear)) {
                    repairList.add(rep);
                } else if (rep.getCategory().getCategory().toLowerCase(Locale.ROOT)
                        .contains(attrsClear)) {
                    repairList.add(rep);
                } else if (rep.getDevice().getName().toLowerCase(Locale.ROOT)
                        .contains(attrsClear)) {
                    repairList.add(rep);
                } else if (rep.getRepairedParts()
                        .stream()
                        .anyMatch(detail -> detail.toLowerCase(Locale.ROOT)
                                .contains(attrsClear))) {
                    repairList.add(rep);
                } else if ((rep.getDurability() + "").contains(attrsClear)) {
                    repairList.add(rep);
                }
            }

            model.addAttribute("repairList", repairList);
            if (repairList.isEmpty()) {
                logger.debug("*** RepairController.findRepairByAttrs():  DATA NOT FOUND IN DB***");
                return getRepair(model);
            }
            return "repair";
        } catch (Exception e) {
            logger.error("*** RepairController.findRepairByAttrs():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRepair(model);
        }
    }

}
