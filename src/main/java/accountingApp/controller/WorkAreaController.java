package accountingApp.controller;

import accountingApp.entity.WorkArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.service.WorkAreaService;

import java.util.List;

@Controller
public class WorkAreaController {
	@Autowired
	WorkAreaService workAreaService;

	@GetMapping("/allAegerCaptions")
	public String getAegerCaptions(Model model) {
		List<WorkArea> workAreaList = workAreaService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", workAreaList);
		return "workarea";
	}

	@PostMapping("/addaegercaptions")
	public String addAegerCaptions(@RequestParam String caption,
	                               Model model) {
		WorkArea workArea = new WorkArea(caption);
		workAreaService.addNewAegerCaptions(workArea);
		List<WorkArea> workAreaList = workAreaService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", workAreaList);
		return "workarea";
	}

	@PostMapping("/deleteaegercaptions")
	public String deleteAegerCaptions(@RequestParam int cAeger, Model model) {
		workAreaService.deleteAegerCaptionsById(cAeger);
		List<WorkArea> workAreaList = workAreaService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", workAreaList);
		return "workarea";
	}

	@PostMapping("/updateaegercaptions")
	public String updateAegerCaptions(@RequestParam int cAeger, @RequestParam String caption, Model model) {
		WorkArea workArea = new WorkArea(cAeger, caption);
		workAreaService.updateAegerCaptions(workArea);
		List<WorkArea> workAreaList = workAreaService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", workAreaList);
		return "workarea";
	}

	@PostMapping("findaegercaptionsbyid")
	public String findAegerCaptionsById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<WorkArea> workAreaList = workAreaService.getAegerCaptionsById(findbyid);
			model.addAttribute("aegerCaptionsList", workAreaList);
		} else {
			List<WorkArea> workAreaList = workAreaService.findAllAegerCaptions();
			model.addAttribute("aegerCaptionsList", workAreaList);
		}
		return "workarea";
	}
}
