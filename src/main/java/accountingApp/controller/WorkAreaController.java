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
        String nameWithoutSpaces = name.trim();
        WorkArea workArea = new WorkArea(nameWithoutSpaces);
        workAreaService.addNewWorkArea(workArea);
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("/deleteworkarea")
    public String deleteWorkArea(@RequestParam String id,
                                 Model model) {
        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB ZERO ID***");
            } else {
                workAreaService.deleteWorkAreaById(idCheck);
                List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
                model.addAttribute("workAreaList", workAreaList);
            }
            return "workarea";

        } catch (Exception e) {
            System.out.println("*** WRONG ID TYPE ***");
            return "workarea";
        }
    }

    @PostMapping("/updateworkarea")
    public String updateWorkArea(@RequestParam String id,
                                 @RequestParam String name,
                                 Model model) {
        String idWithoutSpaces = id.trim();
        String nameWithoutSpaces = name.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB-ZERO ID ***");
            } else {
                WorkArea workArea = new WorkArea(idCheck, nameWithoutSpaces);
                workAreaService.updateWorkArea(workArea);
                List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
                model.addAttribute("workAreaList", workAreaList);
            }
            return "workarea";
        } catch (Exception e) {
            System.out.println("*** WRONG ID TYPE ***");
            return "workarea";
        }
    }

    @PostMapping("/findworkareabyname")
    public String findAreaByName(@RequestParam String name,
                                 Model model) {
        String nameWithoutSpaces = name.trim();
        try {
            int idCheck = Integer.parseInt(nameWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB-ZERO ID ***");
            } else {
                System.out.println("*** FOUND BY ID ***");
                List<WorkArea> workAreaList;
                workAreaList = workAreaService.getWorkAreaById(idCheck);
                model.addAttribute("workAreaList", workAreaList);
            }
            return "workarea";
        } catch (Exception e) {
            System.out.println("*** FOUND BY NAME ***");
            List<WorkArea> workAreaList = workAreaService.getWorkAreaByName(name);
            model.addAttribute("workAreaList", workAreaList);
            return "workarea";
        }
    }
}
