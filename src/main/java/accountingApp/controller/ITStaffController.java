package accountingApp.controller;

import accountingApp.entity.ITStaff;
import accountingApp.entity.Profession;
import accountingApp.usefulmethods.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.ITStaffService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ITStaffController {

    private final Logger logger = LoggerFactory.getLogger(ITStaffController.class);

    @Autowired
    private ITStaffService ITStaffService;
    @Autowired
    private Checker checker;

    @GetMapping("/itstaff")
    public String getItStaff(Model model) {
        List<ITStaff> ITStaffList = ITStaffService.getAllItStaff();

        Profession[] professionsArray = Profession.values();
        List<String> professionList = new ArrayList<>();

        for (Profession prf : professionsArray
        ) {
            professionList.add(prf.getProfession());
        }

        model.addAttribute("itStaffList", ITStaffList);
        model.addAttribute("professionList", professionList);
        return "itstaff";
    }

    @PostMapping("/additstaff")
    public String addItStaff(@RequestParam String name
            , @RequestParam String profession
            , Model model) {
        if (checker.checkAttribute(name)
                || checker.checkAttribute(profession)
        ) {
            logger.warn("*** ITStaffController.addItStaff():  Attribute has a null value! ***");
            return getItStaff(model);
        }
        try {
            String nameWithoutSpaces = name.trim();

            if (checker.checkAttribute(nameWithoutSpaces)) {
                logger.warn("*** ITStaffController.addItStaff(): EMPTY NAME ***");
                return getItStaff(model);
            } else if (nameWithoutSpaces.matches("\\d*")) {
                logger.warn("*** ITStaffController.addItStaff(): NAME MATCHES FIGURES ***");
                return getItStaff(model);
            } else {
                Profession prof = Profession.Преподаватель;
                Profession[] professionsArray = Profession.values();

                for (Profession prf : professionsArray
                ) {
                    if (prf.getProfession().equals(profession)) {
                        prof = prf;
                        break;
                    }
                }

                ITStaff ITStaff = new ITStaff(nameWithoutSpaces, prof);
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

        if (checker.checkAttribute(id)
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
    public String updateItStaff(@RequestParam String id
            , @RequestParam String name
            , @RequestParam String profession
            , Model model) {

        if (checker.checkAttribute(id)
                || checker.checkAttribute(name)
                || checker.checkAttribute(profession)
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

                Profession prof = Profession.Преподаватель;
                Profession[] professionsArray = Profession.values();

                for (Profession prf : professionsArray
                ) {
                    if (prf.getProfession().equals(profession)) {
                        prof = prf;
                        break;
                    }
                }

                ITStaff ITStaff = new ITStaff(idCheck, name, prof);
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

        if (checker.checkAttribute(id)
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

