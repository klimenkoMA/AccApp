package accountingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.entity.ProfCaptions;
import accountingApp.service.ProfCaptionsService;

import java.util.List;

@Controller
public class ProfCaptionsController {
	@Autowired
	ProfCaptionsService profCaptionsService;

	@GetMapping("/allProfCaptions")
	public String getProfCaptions(Model model) {
		List<ProfCaptions> profCaptionsList = profCaptionsService.findAllProfCaptions();
		model.addAttribute("profCaptionsList", profCaptionsList);
		return "profcaptions";
	}

	@PostMapping("/addprofcaptions")
	public String addProfCaptions(@RequestParam String caption,
	                              Model model) {
		ProfCaptions profCaptions = new ProfCaptions(caption);
		profCaptionsService.addNewProfCaptions(profCaptions);
		List<ProfCaptions> profCaptionsList = profCaptionsService.findAllProfCaptions();
		model.addAttribute("profCaptionsList", profCaptionsList);
		return "profcaptions";
	}

	@PostMapping("/deleteprofcaptions")
	public String deleteProfCaptions(@RequestParam int cProf, Model model) {
		profCaptionsService.deleteProfCaptionsById(cProf);
		List<ProfCaptions> profCaptionsList = profCaptionsService.findAllProfCaptions();
		model.addAttribute("profCaptionsList", profCaptionsList);
		return "profcaptions";
	}

	@PostMapping("/updateprofcaptions")
	public String updateProfCaptions(@RequestParam int cProf, @RequestParam String caption, Model model) {
		ProfCaptions profCaptions = new ProfCaptions(cProf, caption);
		profCaptionsService.updateProfCaptions(profCaptions);
		List<ProfCaptions> profCaptionsList = profCaptionsService.findAllProfCaptions();
		model.addAttribute("profCaptionsList", profCaptionsList);
		return "profcaptions";
	}

	@PostMapping("findprofcaptionsbyid")
	public String findProfCaptionsById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<ProfCaptions> profCaptionsList = profCaptionsService.getProfCaptionsById(findbyid);
			model.addAttribute("profCaptionsList", profCaptionsList);
		} else {
			List<ProfCaptions> profCaptionsList = profCaptionsService.findAllProfCaptions();
			model.addAttribute("profCaptionsList", profCaptionsList);
		}
		return "profcaptions";
	}
}
