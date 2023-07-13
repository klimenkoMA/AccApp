package accountingApp.controller;

import accountingApp.entity.Events;
import accountingApp.entity.WorkArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.service.WorkAreaService;
import accountingApp.service.EventsService;

import java.util.List;

@Controller
public class EventsController {
	@Autowired
	EventsService eventsService;
	@Autowired
	WorkAreaService workAreaService;

	@GetMapping("/allRecreantsAegers")
	public String getRecreantsAegers(Model model) {
		List<Events> eventsList = eventsService.findAllRecreantsAegers();
		model.addAttribute("recreantsAegersList", eventsList);
		return "events";
	}

//	@PostMapping("/addrecreantsaegers")
//	public String addAegerCaptions(@RequestParam int waypaperSnnId,
//	                               @RequestParam int cAegerId,
//	                               Model model) {
//		List<WorkArea> workAreaList = workAreaService.getAegerCaptionsById(cAegerId);
//		Events events = new Events(workAreaList.get(0));
//		eventsService.addNewRecreantsAegers(events);
//		List<Events> eventsList = eventsService.findAllRecreantsAegers();
//		model.addAttribute("recreantsAegersList", eventsList);
//		return "events";
//	}

	@PostMapping("/deleterecreantsaegers")
	public String deleteAegerCaptions(@RequestParam int id, Model model) {
		eventsService.deleteRecreantsAegersById(id);
		List<Events> eventsList = eventsService.findAllRecreantsAegers();
		model.addAttribute("recreantsAegersList", eventsList);
		return "events";
	}

//	@PostMapping("/updaterecreantsaegers")
//	public String updateAegerCaptions(@RequestParam int id,
//	                                  @RequestParam int waypaperSnnId,
//	                                  @RequestParam int cAegerId,
//	                                  Model model) {
//		List<WorkArea> workAreaList = workAreaService.getAegerCaptionsById(cAegerId);
//		Events events = new Events(id,workAreaList.get(0));
//		eventsService.addNewRecreantsAegers(events);
//		List<Events> eventsList = eventsService.findAllRecreantsAegers();
//		model.addAttribute("recreantsAegersList", eventsList);
//		return "events";
//	}

	@PostMapping("findrecreantsaegersbyid")
	public String findRecreantsAegersById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<Events> eventsList = eventsService.getRecreantsAegersById(findbyid);
			model.addAttribute("recreantsAegersList", eventsList);
		} else {
			List<Events> eventsList = eventsService.findAllRecreantsAegers();
			model.addAttribute("recreantsAegersList", eventsList);
		}
		return "events";
	}
}
