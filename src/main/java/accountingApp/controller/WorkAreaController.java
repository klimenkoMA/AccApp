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
        WorkArea workArea = new WorkArea(name);
        workAreaService.addNewWorkArea(workArea);
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("/deleteworkarea")
    public String deleteWorkArea(@RequestParam int id,
                                 Model model) {
        workAreaService.deleteWorkAreaById(id);
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("/updateworkarea")
    public String updateWorkArea(@RequestParam int id,
                                 @RequestParam String name,
                                 Model model) {
        WorkArea workArea = new WorkArea(id, name);
        workAreaService.updateWorkArea(workArea);
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("findworkareabyid")
    public String findAreaById(@RequestParam int id,
                               Model model) {
        List<WorkArea> workAreaList;
        if (id > 0) {
            workAreaList = workAreaService.getWorkAreaById(id);
        } else {
            workAreaList = workAreaService.findAllWorkArea();
        }
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("/findworkareabyname")
    public String findAreaByName(@RequestParam String name,
                                 Model model){
        List<WorkArea> workAreaList = workAreaService.getWorkAreaByName(name);
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }
}
