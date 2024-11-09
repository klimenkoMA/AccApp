package accountingApp.controller;

import accountingApp.entity.ITStaff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.ITStaffService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ITStaffController {

    Logger logger = LoggerFactory.getLogger(ITStaffController.class);

    @Autowired
    ITStaffService ITStaffService;

    @GetMapping("/itstaff")
    public String getItStaff(Model model) {
        List<ITStaff> ITStaffList = ITStaffService.getAllItStaff();
        model.addAttribute("itStaffList", ITStaffList);
        return "itstaff";
    }

    @PostMapping("/additstaff")
    public String addItStaff(@RequestParam String name,
                             Model model) {

        if (name == null
        ) {
            logger.warn("*** ITStaffController.addItStaff():  Attribute has a null value! ***");
            return getItStaff(model);
        }
        try {
            String nameWithoutSpaces = name.trim();

            if (nameWithoutSpaces.equals("")) {
                logger.warn("*** ITStaffController.addItStaff(): EMPTY NAME ***");
                return getItStaff(model);
            } else if (nameWithoutSpaces.matches("\\d*")) {
                logger.warn("*** ITStaffController.addItStaff(): NAME MATCHES FIGURES ***");
                return getItStaff(model);
            } else {
                ITStaff ITStaff = new ITStaff(name);
                ITStaffService.addNewItStaff(ITStaff);

                return getItStaff(model);
            }
        } catch (Exception e) {
            logger.error("*** ITStaffController.addItStaff():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getItStaff(model);
        }
    }

    @PostMapping("/deleteitstaff")
    public String deleteITStaff(@RequestParam String id,
                                Model model) {

        if (id == null
        ) {
            logger.warn("*** ITStaffController.deleteITStaff():  Attribute has a null value! ***");
            return getItStaff(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** ITStaffController.deleteITStaff(): ID is SUBZERO***");
            } else {
                ITStaffService.deleteITStaffById(idCheck);
            }
            return getItStaff(model);
        } catch (Exception e) {
            logger.error("*** ITStaffController.deleteITStaff():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getItStaff(model);
        }
    }

    @PostMapping("/updateitstaff")
    public String updateItStaff(@RequestParam String id,
                                @RequestParam String name,
                                Model model) {

        if (id == null
                || name == null
        ) {
            logger.warn("*** ITStaffController.updateItStaff():  Attribute has a null value! ***");
            return getItStaff(model);
        }

        String idWithoutSpaces = id.trim();
        String nameWithoutSpaces = name.trim();

        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** ITStaffController.updateItStaff(): ID is SUBZERO***");
            } else if (nameWithoutSpaces.matches("\\d*")) {
                logger.warn("*** ITStaffController.updateItStaff(): NAME MATCHES FIGURES ***");
            } else {
                ITStaff ITStaff = new ITStaff(idCheck, name);
                ITStaffService.updateItStaff(ITStaff);
            }
            return getItStaff(model);
        } catch (Exception e) {
            logger.error("*** ITStaffController.updateItStaff():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getItStaff(model);
        }
    }

    @PostMapping("/finditstaffbyid")
    public String findItStaffById(@RequestParam String id,
                                  Model model) {

        if (id == null
        ) {
            logger.warn("*** ITStaffController.findItStaffById():  Attribute has a null value! ***");
            return getItStaff(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** ITStaffController.findItStaffById(): ID is SUBZERO***");
                return getItStaff(model);
            } else {
                logger.debug("*** ITStaffController.findItStaffById():" +
                        "found an ItStaff by ID ***");
                List<ITStaff> ITStaffList;
                ITStaffList = ITStaffService.getITStaffById(idCheck);
                model.addAttribute("itStaffList", ITStaffList);
                return "itstaff";
            }
        } catch (Exception e) {

            if (idWithoutSpaces.matches("\\d*")) {
                logger.warn("*** ITStaffController.findItStaffById():" +
                        " NAME MATCHES FIGURES ***");
                return getItStaff(model);
            }
            try {
                logger.debug("*** ITStaffController.findItStaffById():" +
                        "found an ItStaff by NAME ***");
                List<ITStaff> ITStaffList = ITStaffService.getITStaffByName(idWithoutSpaces);
                model.addAttribute("itStaffList", ITStaffList);
                return "itstaff";
            } catch (Exception e1) {
                logger.error("*** ITStaffController.findItStaffById():  WRONG DB VALUES*** "
                        + e1.getMessage());
                return getItStaff(model);
            }
        }
    }
}

