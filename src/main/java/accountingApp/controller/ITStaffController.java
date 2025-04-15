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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class ITStaffController {

    private final Logger logger = LoggerFactory.getLogger(ITStaffController.class);

    @Autowired
    private ITStaffService itStaffService;
    @Autowired
    private Checker checker;

    @GetMapping("/itstaff")
    public String getItStaff(Model model) {
        List<ITStaff> ITStaffList = itStaffService.getAllItStaff();

        List<String> professionList = Arrays.stream(Profession.values())
                .map(Profession::getProfession)
                .collect(Collectors.toList());

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

                Profession prof = Arrays.stream(Profession.values())
                        .filter(prf -> prf.getProfession().equals(profession))
                        .findFirst()
                        .orElse(Profession.Преподаватель);

                ITStaff ITStaff = new ITStaff(nameWithoutSpaces, prof);
                itStaffService.addNewItStaff(ITStaff);

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
                itStaffService.deleteITStaffById(idCheck);
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

                Profession prof = Arrays.stream(Profession.values())
                        .filter(prf -> prf.getProfession().equals(profession))
                        .findFirst()
                        .orElse(Profession.Преподаватель);

                ITStaff ITStaff = new ITStaff(idCheck, name, prof);
                itStaffService.updateItStaff(ITStaff);
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
                ITStaffList = itStaffService.getITStaffById(idCheck);
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
                List<ITStaff> ITStaffList = itStaffService.getITStaffByName(idWithoutSpaces);
                model.addAttribute("itStaffList", ITStaffList);
                return "itstaff";
            } catch (Exception e1) {
                logger.error("*** ITStaffController.findItStaffById():  WRONG DB VALUES*** "
                        + e1.getMessage());
                return getItStaff(model);
            }
        }
    }

    @PostMapping("/finditstaffbyprofession")
    public String getItStaffListByProfession(@RequestParam String profession
            , Model model) {

        if (checker.checkAttribute(profession)
        ) {
            logger.warn("*** ITStaffController.getItStaffListByProfession():  Attribute has a null value! ***");
            return getItStaff(model);
        }
        String professionWithOutSpaces = profession.trim();

        try {

            Profession prof = Arrays.stream(Profession.values())
                    .filter(prf -> prf.getProfession().equals(professionWithOutSpaces))
                    .findFirst()
                    .orElse(Profession.Преподаватель);

            List<ITStaff> ITStaffList = itStaffService.getItStaffByProfession(prof);
            model.addAttribute("itStaffList", ITStaffList);

            return "itstaff";

        } catch (Exception e) {
            logger.error("*** ITStaffController.getItStaffListByProfession():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getItStaff(model);
        }
    }

    @PostMapping("/finditstaffbyattrs")
    public String getItStaffListByAttrs(@RequestParam String attrs
            , Model model) {

        if (checker.checkAttribute(attrs)
        ) {
            logger.warn("*** ITStaffController.getItStaffListByAttrs():  Attribute has a null value! ***");
            return getItStaff(model);
        }
        String attrWithOutSpaces = attrs.trim().toLowerCase(Locale.ROOT);

        try {
            List<ITStaff> itStaffs = itStaffService.getAllItStaff();

            List<ITStaff> itStaffList = new ArrayList<>();

            for (ITStaff it : itStaffs
            ) {
                if ((it.getId() + "").contains(attrWithOutSpaces)) {
                    itStaffList.add(it);
                } else if (it.getProfession().name().toLowerCase(Locale.ROOT)
                        .contains(attrWithOutSpaces)) {
                    itStaffList.add(it);
                } else if (it.getName().toLowerCase(Locale.ROOT)
                        .contains(attrWithOutSpaces)) {
                    itStaffList.add(it);
                }
            }

            model.addAttribute("itStaffList", itStaffList);
            if (itStaffList.isEmpty()) {
                logger.debug("*** ITStaffController.getItStaffListByAttrs():  DATA NOT FOUND IN DB***");
                return getItStaff(model);
            }
            return "itstaff";
        } catch (Exception e) {
            logger.error("*** ITStaffController.getItStaffListByAttrs():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getItStaff(model);
        }
    }
}

