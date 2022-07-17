package recproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recproj.entity.ProcedCaptions;
import recproj.entity.ProceduresAssigned;
import recproj.entity.Recreants;
import recproj.service.ProcedCaptionsService;
import recproj.service.ProceduresAssignedService;
import recproj.service.RecreantsService;

import java.util.List;

@Controller
public class ProceduresAssignedController {
	@Autowired
	ProceduresAssignedService proceduresAssignedService;
	@Autowired
	RecreantsService recreantsService;
	@Autowired
	ProcedCaptionsService procedCaptionsService;

	@GetMapping("/allProceduresAssigned")
	public String getProceduresAssigned(Model model) {
		List<ProceduresAssigned> proceduresAssignedList = proceduresAssignedService.findAllProceduresAssigned();
		model.addAttribute("proceduresAssignedList", proceduresAssignedList);
		return "proceduresassigned";
	}

	@PostMapping("/addproceduresassigned")
	public String addProceduresAssigned(@RequestParam int nAssigned,
	                                    @RequestParam int nPassed,
	                                    @RequestParam String comment,
	                                    @RequestParam int waypaperSnnId,
	                                    @RequestParam int cProcId,
	                                    Model model) {
		List<Recreants> recreantsList = recreantsService.getRecreantsById(waypaperSnnId);
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.getProcedCaptionsById(cProcId);
		ProceduresAssigned proceduresAssigned = new ProceduresAssigned(nAssigned, nPassed, comment,
				recreantsList.get(0), procedCaptionsList.get(0));
		proceduresAssignedService.addNewProceduresAssigned(proceduresAssigned);
		List<ProceduresAssigned> proceduresAssignedList = proceduresAssignedService.findAllProceduresAssigned();
		model.addAttribute("proceduresAssignedList", proceduresAssignedList);
		return "proceduresassigned";
	}

	@PostMapping("/deleteproceduresassigned")
	public String deleteProceduresAssigned(@RequestParam int id,
	                                       Model model) {
		proceduresAssignedService.deleteProceduresAssignedById(id);
		List<ProceduresAssigned> proceduresAssignedList = proceduresAssignedService.findAllProceduresAssigned();
		model.addAttribute("proceduresAssignedList", proceduresAssignedList);
		return "proceduresassigned";
	}

	@PostMapping("/updateproceduresassigned")
	public String updateProceduresAssigned(@RequestParam int id,
	                                       @RequestParam int nAssigned,
	                                       @RequestParam int nPassed,
	                                       @RequestParam String comment,
	                                       @RequestParam int waypaperSnnId,
	                                       @RequestParam int cProcId,
	                                       Model model) {
		List<Recreants> recreantsList = recreantsService.getRecreantsById(waypaperSnnId);
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.getProcedCaptionsById(cProcId);
		ProceduresAssigned proceduresAssigned = new ProceduresAssigned(id, nAssigned, nPassed, comment,
				recreantsList.get(0), procedCaptionsList.get(0));
		proceduresAssignedService.addNewProceduresAssigned(proceduresAssigned);
		List<ProceduresAssigned> proceduresAssignedList = proceduresAssignedService.findAllProceduresAssigned();
		model.addAttribute("proceduresAssignedList", proceduresAssignedList);
		return "proceduresassigned";
	}

	@PostMapping("findproceduresassignedbyid")
	public String findProceduresAssignedById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<ProceduresAssigned> proceduresAssignedList = proceduresAssignedService.getProceduresAssignedById(findbyid);
			model.addAttribute("proceduresAssignedList", proceduresAssignedList);
		} else {
			List<ProceduresAssigned> proceduresAssignedList = proceduresAssignedService.findAllProceduresAssigned();
			model.addAttribute("proceduresAssignedList", proceduresAssignedList);
		}
		return "proceduresassigned";
	}
}
