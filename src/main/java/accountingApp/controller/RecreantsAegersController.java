package accountingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.entity.AegerCaptions;
import accountingApp.entity.Recreants;
import accountingApp.entity.RecreantsAegers;
import accountingApp.service.AegerCaptionsService;
import accountingApp.service.RecreantsAegersService;
import accountingApp.service.RecreantsService;

import java.util.List;

@Controller
public class RecreantsAegersController {
	@Autowired
	RecreantsAegersService recreantsAegersService;
	@Autowired
	RecreantsService recreantsService;
	@Autowired
	AegerCaptionsService aegerCaptionsService;

	@GetMapping("/allRecreantsAegers")
	public String getRecreantsAegers(Model model) {
		List<RecreantsAegers> recreantsAegersList = recreantsAegersService.findAllRecreantsAegers();
		model.addAttribute("recreantsAegersList", recreantsAegersList);
		return "recreantsaegers";
	}

	@PostMapping("/addrecreantsaegers")
	public String addAegerCaptions(@RequestParam int waypaperSnnId,
	                               @RequestParam int cAegerId,
	                               Model model) {
		List<Recreants> recreantsList = recreantsService.getRecreantsById(waypaperSnnId);
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.getAegerCaptionsById(cAegerId);
		RecreantsAegers recreantsAegers = new RecreantsAegers(recreantsList.get(0), aegerCaptionsList.get(0));
		recreantsAegersService.addNewRecreantsAegers(recreantsAegers);
		List<RecreantsAegers> recreantsAegersList = recreantsAegersService.findAllRecreantsAegers();
		model.addAttribute("recreantsAegersList", recreantsAegersList);
		return "recreantsaegers";
	}

	@PostMapping("/deleterecreantsaegers")
	public String deleteAegerCaptions(@RequestParam int id, Model model) {
		recreantsAegersService.deleteRecreantsAegersById(id);
		List<RecreantsAegers> recreantsAegersList = recreantsAegersService.findAllRecreantsAegers();
		model.addAttribute("recreantsAegersList", recreantsAegersList);
		return "recreantsaegers";
	}

	@PostMapping("/updaterecreantsaegers")
	public String updateAegerCaptions(@RequestParam int id,
	                                  @RequestParam int waypaperSnnId,
	                                  @RequestParam int cAegerId,
	                                  Model model) {
		List<Recreants> recreantsList = recreantsService.getRecreantsById(waypaperSnnId);
		List<AegerCaptions> aegerCaptionsList = aegerCaptionsService.getAegerCaptionsById(cAegerId);
		RecreantsAegers recreantsAegers = new RecreantsAegers(id, recreantsList.get(0), aegerCaptionsList.get(0));
		recreantsAegersService.addNewRecreantsAegers(recreantsAegers);
		List<RecreantsAegers> recreantsAegersList = recreantsAegersService.findAllRecreantsAegers();
		model.addAttribute("recreantsAegersList", recreantsAegersList);
		return "recreantsaegers";
	}

	@PostMapping("findrecreantsaegersbyid")
	public String findRecreantsAegersById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<RecreantsAegers> recreantsAegersList = recreantsAegersService.getRecreantsAegersById(findbyid);
			model.addAttribute("recreantsAegersList", recreantsAegersList);
		} else {
			List<RecreantsAegers> recreantsAegersList = recreantsAegersService.findAllRecreantsAegers();
			model.addAttribute("recreantsAegersList", recreantsAegersList);
		}
		return "recreantsaegers";
	}
}
