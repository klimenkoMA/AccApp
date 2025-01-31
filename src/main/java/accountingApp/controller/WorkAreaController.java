package accountingApp.controller;

import accountingApp.entity.WorkArea;
import accountingApp.usefulmethods.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.WorkAreaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class WorkAreaController {

    private final Logger logger = LoggerFactory.getLogger(WorkAreaController.class);

    @Autowired
    private WorkAreaService workAreaService;
    @Autowired
    private Checker checker;

    @GetMapping("/workarea")
    public String getWorkArea(Model model) {
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("/addworkarea")
    public String addWorkArea(@RequestParam String name,
                              @RequestParam String description,
                              Model model) {

        if (checker.checkAttribute(name)
                || checker.checkAttribute(description)
        ) {
            logger.warn("*** WorkAreaController.addWorkArea():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String nameWithoutSpaces = name.trim();
        String descriptionWithoutSpaces = description.trim();
        if (checker.checkAttribute(nameWithoutSpaces)
                || checker.checkAttribute(descriptionWithoutSpaces)) {
            logger.warn("*** WorkAreaController.addWorkArea(): EMPTY NAME ***");
            return getWorkArea(model);
        }

        try {
            WorkArea workArea = new WorkArea(nameWithoutSpaces, descriptionWithoutSpaces);
            workAreaService.addNewWorkArea(workArea);

            return getWorkArea(model);
        } catch (Exception e) {
            logger.error("*** WorkAreaController.addWorkArea():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getWorkArea(model);
        }
    }

    @PostMapping("/deleteworkarea")
    public String deleteWorkArea(@RequestParam String id,
                                 Model model) {

        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** WorkAreaController.deleteWorkArea():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** WorkAreaController.deleteWorkArea(): ID is SUBZERO***");
            } else {
                workAreaService.deleteWorkAreaById(idCheck);
            }
            return getWorkArea(model);

        } catch (Exception e) {
            logger.error("*** WorkAreaController.deleteWorkArea():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getWorkArea(model);
        }
    }

    @PostMapping("/updateworkarea")
    public String updateWorkArea(@RequestParam String id,
                                 @RequestParam String name,
                                 @RequestParam String description,
                                 Model model) {

        if (checker.checkAttribute(id)
                || checker.checkAttribute(name)
                || checker.checkAttribute(description)
        ) {
            logger.warn("*** WorkAreaController.updateWorkArea():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String nameWithoutSpaces = name.trim();
        String idWithoutSpaces = id.trim();
        String descriptionWithoutSpaces = description.trim();

        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** WorkAreaController.updateWorkArea(): ID is SUBZERO***");
                return getWorkArea(model);
            } else {
                if (checker.checkAttribute(nameWithoutSpaces)
                        || checker.checkAttribute(descriptionWithoutSpaces)) {
                    logger.warn("*** WorkAreaController.updateWorkArea(): NAME is EMPTY ***");
                    return getWorkArea(model);
                }
                WorkArea workArea = new WorkArea(idCheck, nameWithoutSpaces, descriptionWithoutSpaces);
                workAreaService.updateWorkArea(workArea);
            }
            return getWorkArea(model);
        } catch (Exception e) {
            logger.error("*** WorkAreaController.updateWorkArea():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getWorkArea(model);
        }
    }

    @PostMapping("/findworkareabyname")
    public String findAreaByName(@RequestParam String name,
                                 Model model) {

        if (checker.checkAttribute(name)
        ) {
            logger.warn("*** WorkAreaController.findAreaByName():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String nameWithoutSpaces = name.trim();
        try {
            int idCheck = Integer.parseInt(nameWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** WorkAreaController.findAreaByName(): ID is SUBZERO***");
                return getWorkArea(model);
            } else {
                logger.debug("*** WorkAreaController.findAreaByName(): FOUND BY ID ***");
                List<WorkArea> workAreaList;
                workAreaList = workAreaService.getWorkAreaById(idCheck);
                model.addAttribute("workAreaList", workAreaList);
            }
            return "workarea";
        } catch (Exception e) {
            try {
                logger.debug("*** WorkAreaController.findAreaByName(): FOUND BY NAME ***"
                        + e.getMessage());
                List<WorkArea> workAreaList = workAreaService.getWorkAreaByName(name);

                if (workAreaList.isEmpty()) {
                    return getWorkArea(model);
                }
                model.addAttribute("workAreaList", workAreaList);
                return "workarea";
            } catch (Exception e1) {
                logger.error("*** WorkAreaController.findAreaByName():  WRONG DB VALUES*** "
                        + e1.getMessage());
                return getWorkArea(model);
            }
        }
    }

    @PostMapping("/findworkareabyattrs")
    public String findAreaByAttrs(@RequestParam String attrs,
                                  Model model) {

        if (checker.checkAttribute(attrs)
        ) {
            logger.warn("*** WorkAreaController.findAreaByAttrs():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String attrsWithoutSpaces = attrs.trim().toLowerCase(Locale.ROOT);
        try {
            List<WorkArea> workAreas = workAreaService.findAllWorkArea();
            List<WorkArea> workAreaList = new ArrayList<>();

            for (WorkArea wr : workAreas
            ) {
                if ((wr.getId() + "").contains(attrsWithoutSpaces)) {
                    workAreaList.add(wr);
                } else if (wr.getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    workAreaList.add(wr);
                } else if (wr.getDescription().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces))
                    workAreaList.add(wr);
            }

            if (workAreaList.isEmpty()) {
                logger.debug("*** WorkAreaController.findAreaByAttrs():  DATA NOT FOUND IN DB***");
                return getWorkArea(model);
            }

            model.addAttribute("workAreaList", workAreaList);
            return "workarea";
        } catch (Exception e1) {
            logger.error("*** WorkAreaController.findAreaByAttrs():  WRONG DB VALUES*** "
                    + e1.getMessage());
            return getWorkArea(model);
        }
    }

}
