package accountingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.entity.AegerCaptions;
import accountingApp.service.AegerCaptionsService;

import java.util.List;

@Controller
public class AegerCaptionsController {
	@Autowired
	AegerCaptionsService aegerCaptionsService;

	@GetMapping("/allAegerCaptions")
	public String getAegerCaptions(Model model) {
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", aegerCaptionsList);
		return "aegercaptions";
	}

	@PostMapping("/addaegercaptions")
	public String addAegerCaptions(@RequestParam String caption,
	                               Model model) {
		AegerCaptions aegerCaptions = new AegerCaptions(caption);
		aegerCaptionsService.addNewAegerCaptions(aegerCaptions);
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", aegerCaptionsList);
		return "aegercaptions";
	}

	@PostMapping("/deleteaegercaptions")
	public String deleteAegerCaptions(@RequestParam int cAeger, Model model) {
		aegerCaptionsService.deleteAegerCaptionsById(cAeger);
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", aegerCaptionsList);
		return "aegercaptions";
	}

	@PostMapping("/updateaegercaptions")
	public String updateAegerCaptions(@RequestParam int cAeger, @RequestParam String caption, Model model) {
		AegerCaptions aegerCaptions = new AegerCaptions(cAeger, caption);
		aegerCaptionsService.updateAegerCaptions(aegerCaptions);
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.findAllAegerCaptions();
		model.addAttribute("aegerCaptionsList", aegerCaptionsList);
		return "aegercaptions";
	}

	@PostMapping("findaegercaptionsbyid")
	public String findAegerCaptionsById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.getAegerCaptionsById(findbyid);
			model.addAttribute("aegerCaptionsList", aegerCaptionsList);
		} else {
			List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.findAllAegerCaptions();
			model.addAttribute("aegerCaptionsList", aegerCaptionsList);
		}
		return "aegercaptions";
	}
}
