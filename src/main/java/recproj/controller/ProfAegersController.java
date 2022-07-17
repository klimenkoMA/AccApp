package recproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recproj.entity.AegerCaptions;
import recproj.entity.ProfAegers;
import recproj.entity.ProfCaptions;
import recproj.service.AegerCaptionsService;
import recproj.service.ProfAegersService;
import recproj.service.ProfCaptionsService;

import java.util.List;

@Controller
public class ProfAegersController {
	@Autowired
	ProfAegersService profAegersService;
	@Autowired
	ProfCaptionsService profCaptionsService;
	@Autowired
	AegerCaptionsService aegerCaptionsService;

	@GetMapping("/allProfAegers")
	public String getProfAegers(Model model) {
		List<ProfAegers> profAegersList = profAegersService.findAllProfAegers();
		model.addAttribute("profAegersList", profAegersList);
		return "profaegers";
	}

	@PostMapping("/addprofaegers")
	public String addAegerCaptions(@RequestParam int cProfId,
	                               @RequestParam int cAegerId, Model model) {
		List<ProfCaptions> profCaptionsList = profCaptionsService.getProfCaptionsById(cProfId);
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.getAegerCaptionsById(cAegerId);
		ProfAegers profAegers = new ProfAegers(profCaptionsList.get(0), aegerCaptionsList.get(0));
		profAegersService.addNewProfAegers(profAegers);
		List<ProfAegers> profAegersList = profAegersService.findAllProfAegers();
		model.addAttribute("profAegersList", profAegersList);
		return "profaegers";
	}

	@PostMapping("/deleteprofaegers")
	public String deleteAegerCaptions(@RequestParam int id, Model model) {
		profAegersService.deleteProfAegersById(id);
		List<ProfAegers> profAegersList = profAegersService.findAllProfAegers();
		model.addAttribute("profAegersList", profAegersList);
		return "profaegers";
	}

	@PostMapping("/updateprofaegers")
	public String updateAegerCaptions(@RequestParam int id,
	                                  @RequestParam int cProfId,
	                                  @RequestParam int cAegerId, Model model) {
		List<ProfCaptions> profCaptionsList = profCaptionsService.getProfCaptionsById(cProfId);
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.getAegerCaptionsById(cAegerId);
		ProfAegers profAegers = new ProfAegers(id, profCaptionsList.get(0), aegerCaptionsList.get(0));
		profAegersService.updateProfAegers(profAegers);
		List<ProfAegers> profAegersList = profAegersService.findAllProfAegers();
		model.addAttribute("profAegersList", profAegersList);
		return "profaegers";
	}

	@PostMapping("findprofaegersbyid")
	public String findAegerCaptionsById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<ProfAegers> profAegersList = profAegersService.getProfAegerssById(findbyid);
			model.addAttribute("profAegersList", profAegersList);
		} else {
			List<ProfAegers> profAegersList = profAegersService.findAllProfAegers();
			model.addAttribute("profAegersList", profAegersList);
		}
		return "profaegers";
	}
}
