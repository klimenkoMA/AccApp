package accountingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.entity.ProcedCaptions;
import accountingApp.service.ProcedCaptionsService;

import java.util.List;

@Controller
public class ProcedCaptionsController {
	@Autowired
	ProcedCaptionsService procedCaptionsService;

	@GetMapping("/allProcedCaptions")
	public String getProcedCaptions(Model model) {
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", procedCaptionsList);
		return "procedcaptions";
	}

	@PostMapping("/addprocedcaptions")
	public String addProcedCaptions(@RequestParam String caption,
	                                Model model) {
		ProcedCaptions procedCaptions = new ProcedCaptions(caption);
		procedCaptionsService.addNewProcedCaptions(procedCaptions);
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", procedCaptionsList);
		return "procedcaptions";
	}

	@PostMapping("/deleteprocedcaptions")
	public String deleteProcedCaptions(@RequestParam int cProc, Model model) {
		procedCaptionsService.deleteProcedCaptionsById(cProc);
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", procedCaptionsList);
		return "procedcaptions";
	}

	@PostMapping("/updateprocedcaptions")
	public String updateProcedCaptions(@RequestParam int cProc, @RequestParam String caption, Model model) {
		ProcedCaptions procedCaptions = new ProcedCaptions(cProc, caption);
		procedCaptionsService.updateProcedCaptions(procedCaptions);
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", procedCaptionsList);
		return "procedcaptions";
	}

	@PostMapping("findprocedcaptionsbyid")
	public String findProcedCaptionsById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<ProcedCaptions> procedCaptionsList = procedCaptionsService.getProcedCaptionsById(findbyid);
			model.addAttribute("procedCaptionsList", procedCaptionsList);
		} else {
			List<ProcedCaptions> procedCaptionsList = procedCaptionsService.findAllProcedCaptions();
			model.addAttribute("procedCaptionsList", procedCaptionsList);
		}
		return "procedcaptions";
	}
}
