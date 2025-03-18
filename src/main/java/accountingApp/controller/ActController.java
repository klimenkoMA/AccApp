package accountingApp.controller;

import accountingApp.entity.Act;
import accountingApp.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/acts")
public class ActController {

    @Autowired
    private ActService actService;

    @GetMapping
    public String getAllActs(Model model) {
        model.addAttribute("acts", actService.getAllActs());
        return "acts/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("act", new Act());
        return "acts/form";
    }

    @PostMapping
    public String saveAct(@ModelAttribute Act act) {
        actService.saveAct(act);
        return "redirect:/acts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Act act = actService.getActById(id);
        model.addAttribute("act", act);
        return "acts/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAct(@PathVariable Long id) {
        actService.deleteAct(id);
        return "redirect:/acts";
    }
}