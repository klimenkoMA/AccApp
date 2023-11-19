package accountingApp.controller;

import accountingApp.entity.ITStaff;
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

        String nameWithoutSpaces = name.trim();
        if (nameWithoutSpaces.equals("")) {
            System.out.println("*** EMPTY NAME ***");
            return "itstaff";
        } else if (nameWithoutSpaces.matches("\\d*")) {
            System.out.println("*** NAME MATCHES FIGURES ***");
            return "itstaff";
        } else {
            ITStaff ITStaff = new ITStaff(name);
            ITStaffService.addNewItStaff(ITStaff);
            List<ITStaff> ITStaffList = ITStaffService.getAllItStaff();
            model.addAttribute("itStaffList", ITStaffList);
            return "itstaff";
        }
    }

    @PostMapping("/deleteitstaff")
    public String deleteITStaff(@RequestParam String id,
                                Model model) {
        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("||| SUB ZERO ID |||");
            } else {
                ITStaffService.deleteITStaffById(idCheck);
                List<ITStaff> ITStaffList = ITStaffService.getAllItStaff();
                model.addAttribute("itStaffList", ITStaffList);
            }
            return "itstaff";
        } catch (Exception e) {
            System.out.println("||| WRONG ID TYPE|||");
            return "itstaff";
        }
    }

    @PostMapping("/updateitstaff")
    public String updateItStaff(@RequestParam int id,
                                @RequestParam String name,
                                Model model) {
        ITStaff ITStaff = new ITStaff(id, name);
        ITStaffService.updateItStaff(ITStaff);
        List<ITStaff> ITStaffList = ITStaffService.getAllItStaff();
        model.addAttribute("itStaffList", ITStaffList);
        return "itstaff";
    }

    @PostMapping("/finditstaffbyid")
    public String findPersonById(@RequestParam int id,
                                 Model model) {
        List<ITStaff> ITStaffList;
        if (id > 0) {
            ITStaffList = ITStaffService.getITStaffById(id);
        } else {
            ITStaffList = ITStaffService.getAllItStaff();
        }
        model.addAttribute("itStaffList", ITStaffList);
        return "itstaff";
    }

    @PostMapping("/finditstaffbyname")
    public String findITById(@RequestParam String name,
                             Model model) {
        List<ITStaff> ITStaffList = ITStaffService.getITStaffByName(name);
        model.addAttribute("itStaffList", ITStaffList);
        return "itstaff";
    }
}
