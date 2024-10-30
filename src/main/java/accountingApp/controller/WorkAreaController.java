package accountingApp.controller;

import accountingApp.entity.WorkArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.WorkAreaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WorkAreaController {
    @Autowired
    WorkAreaService workAreaService;


    @GetMapping("/workarea")
    public String getWorkArea(Model model) {
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("/addworkarea")
    public String addWorkArea(@RequestParam String name,
                              Model model) {

        if (name == null
        ) {
            System.out.println("*** WorkAreaController.addWorkArea():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String nameWithoutSpaces = name.trim();
        if (nameWithoutSpaces.equals("") || nameWithoutSpaces.equals(" ")) {
            System.out.println("*** WorkAreaController.addWorkArea(): EMPTY NAME ***");
            return getWorkArea(model);
        }

        try {
            WorkArea workArea = new WorkArea(nameWithoutSpaces);
            workAreaService.addNewWorkArea(workArea);

            return getWorkArea(model);
        } catch (Exception e) {
            System.out.println("*** WorkAreaController.addWorkArea():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getWorkArea(model);
        }
    }

    @PostMapping("/deleteworkarea")
    public String deleteWorkArea(@RequestParam String id,
                                 Model model) {

        if (id == null
        ) {
            System.out.println("*** WorkAreaController.deleteWorkArea():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** WorkAreaController.deleteWorkArea(): ID is SUBZERO***");
            } else {
                workAreaService.deleteWorkAreaById(idCheck);
            }
            return getWorkArea(model);

        } catch (Exception e) {
            System.out.println("*** WorkAreaController.deleteWorkArea():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getWorkArea(model);
        }
    }

    @PostMapping("/updateworkarea")
    public String updateWorkArea(@RequestParam String id,
                                 @RequestParam String name,
                                 Model model) {

        if (id == null
                || name == null
        ) {
            System.out.println("*** WorkAreaController.updateWorkArea():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String nameWithoutSpaces = name.trim();
        String idWithoutSpaces = id.trim();

        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** WorkAreaController.updateWorkArea(): ID is SUBZERO***");
                return getWorkArea(model);
            } else {
                if (nameWithoutSpaces.equals("") || nameWithoutSpaces.equals(" ")) {
                    System.out.println("*** WorkAreaController.updateWorkArea(): NAME is EMPTY ***");
                    return getWorkArea(model);
                }
                WorkArea workArea = new WorkArea(idCheck, nameWithoutSpaces);
                workAreaService.updateWorkArea(workArea);
            }
            return getWorkArea(model);
        } catch (Exception e) {
            System.out.println("*** WorkAreaController.updateWorkArea():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getWorkArea(model);
        }
    }

    @PostMapping("/findworkareabyname")
    public String findAreaByName(@RequestParam String name,
                                 Model model) {

        if (name == null
        ) {
            System.out.println("*** WorkAreaController.findAreaByName():  Attribute has a null value! ***");
            return getWorkArea(model);
        }

        String nameWithoutSpaces = name.trim();
        try {
            int idCheck = Integer.parseInt(nameWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** WorkAreaController.findAreaByName(): ID is SUBZERO***");
                return getWorkArea(model);
            } else {
                System.out.println("*** WorkAreaController.findAreaByName(): FOUND BY ID ***");
                List<WorkArea> workAreaList;
                workAreaList = workAreaService.getWorkAreaById(idCheck);
                model.addAttribute("workAreaList", workAreaList);
            }
            return "workarea";
        } catch (Exception e) {
            try {
                System.out.println("*** WorkAreaController.findAreaByName(): FOUND BY NAME ***");
                System.out.println(e.getMessage());
                List<WorkArea> workAreaList = workAreaService.getWorkAreaByName(name);

                if (workAreaList.isEmpty()) {
                    return getWorkArea(model);
                }
                model.addAttribute("workAreaList", workAreaList);
                return "workarea";
            } catch (Exception e1) {
                System.out.println("*** WorkAreaController.findAreaByName():  WRONG DB VALUES*** "
                        + e1.getMessage());
                return getWorkArea(model);
            }
        }
    }
}
