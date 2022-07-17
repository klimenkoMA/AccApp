package recproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recproj.entity.AegerCaptions;
import recproj.entity.ProcForAegers;
import recproj.entity.ProcedCaptions;
import recproj.service.AegerCaptionsService;
import recproj.service.ProcForAegersService;
import recproj.service.ProcedCaptionsService;

import java.util.List;

@Controller
public class ProcForAegersController {
	@Autowired
	ProcForAegersService procForAegersService;
	@Autowired
	AegerCaptionsService aegerCaptionsService;
	@Autowired
	ProcedCaptionsService procedCaptionsService;

	@GetMapping("/allProcForAegers")
	public String getProcForAegers(Model model) {
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("/addprocforaegers")
	public String addProcForAegers(@RequestParam int aegerCaptionsId,
	                               @RequestParam int procedCaptionsId,
	                               Model model) {
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.getAegerCaptionsById(aegerCaptionsId);
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.getProcedCaptionsById(procedCaptionsId);
		ProcForAegers procForAegers = new ProcForAegers(aegerCaptionsList.get(0), procedCaptionsList.get(0));
		procForAegersService.addNewProcForAegers(procForAegers);
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("/deleteprocforaegers")
	public String deleteProcForAegers(@RequestParam int id, Model model) {
		procForAegersService.deleteProcForAegersById(id);
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("/updateprocforaegers")
	public String updateProcForAegers(@RequestParam int id,
	                                  @RequestParam int aegerCaptionsId,
	                                  @RequestParam int procedCaptionsId, Model model) {
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.getAegerCaptionsById(aegerCaptionsId);
		List<ProcedCaptions> procedCaptionsList = procedCaptionsService.getProcedCaptionsById(procedCaptionsId);
		ProcForAegers procForAegers = new ProcForAegers(id, aegerCaptionsList.get(0), procedCaptionsList.get(0));
		procForAegersService.updateProcForAegers(procForAegers);
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("findprocforaegersbyid")
	public String findProcForAegersById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<ProcForAegers> procForAegersList = procForAegersService.getProcForAegersById(findbyid);
			model.addAttribute("procForAegersList", procForAegersList);
		} else {
			List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
			model.addAttribute("procForAegersList", procForAegersList);
		}
		return "procforaegers";
	}
}
