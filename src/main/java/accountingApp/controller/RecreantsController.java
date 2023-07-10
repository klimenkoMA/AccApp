package accountingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.entity.Employee;
import accountingApp.entity.ProfCaptions;
import accountingApp.entity.Recreants;
import accountingApp.service.EmployeeService;
import accountingApp.service.ProfCaptionsService;
import accountingApp.service.RecreantsService;

import java.util.List;

@Controller
public class RecreantsController {
	@Autowired
	RecreantsService recreantsService;
	@Autowired
	ProfCaptionsService profCaptionsService;
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/allRecreants")
	public String getRecreants(Model model) {
		List<Recreants> recreantsList = recreantsService.findAllRecreants();
		model.addAttribute("recreantsList", recreantsList);
		return "recreants";
	}

	@PostMapping("/addrecreants")
	public String addRecreants(@RequestParam String waypaperSnn,
	                           @RequestParam String fio,
	                           @RequestParam String dBorn,
	                           @RequestParam String liveCity,
	                           @RequestParam int cProfessionId,
	                           @RequestParam int doctorId,
	                           @RequestParam String waypaperDate,
	                           @RequestParam String dArrival,
	                           @RequestParam String dLeave,
	                           @RequestParam String dRevision,
	                           @RequestParam String corpus,
	                           @RequestParam String room,
	                           @RequestParam String place,
	                           Model model) {
		List<ProfCaptions> profCaptionsList = profCaptionsService.getProfCaptionsById(cProfessionId);
		List<Employee> employeeList = employeeService.findPersonsById(doctorId);
		Recreants recreants = new Recreants(waypaperSnn, fio, dBorn, liveCity,
				profCaptionsList.get(0), employeeList.get(0), waypaperDate, dArrival, dLeave, dRevision,
				corpus, room, place);
		recreantsService.addNewARecreants(recreants);
		List<Recreants> recreantsList = recreantsService.findAllRecreants();
		model.addAttribute("recreantsList", recreantsList);
		return "recreants";
	}

	@PostMapping("/deleterecreants")
	public String deleteAegerCaptions(@RequestParam int id, Model model) {
		recreantsService.deleteRecreantsById(id);
		List<Recreants> recreantsList = recreantsService.findAllRecreants();
		model.addAttribute("recreantsList", recreantsList);
		return "recreants";
	}

	@PostMapping("/updaterecreants")
	public String updateAegerCaptions(@RequestParam int id,
	                                  @RequestParam String waypaperSnn,
	                                  @RequestParam String fio,
	                                  @RequestParam String dBorn,
	                                  @RequestParam String liveCity,
	                                  @RequestParam int cProfessionId,
	                                  @RequestParam int doctorId,
	                                  @RequestParam String waypaperDate,
	                                  @RequestParam String dArrival,
	                                  @RequestParam String dLeave,
	                                  @RequestParam String dRevision,
	                                  @RequestParam String corpus,
	                                  @RequestParam String room,
	                                  @RequestParam String place,
	                                  Model model) {
		List<ProfCaptions> profCaptionsList = profCaptionsService.getProfCaptionsById(cProfessionId);
		List<Employee> employeeList = employeeService.findPersonsById(doctorId);
		Recreants recreants = new Recreants(id, waypaperSnn, fio, dBorn, liveCity,
				profCaptionsList.get(0), employeeList.get(0), waypaperDate, dArrival, dLeave, dRevision,
				corpus, room, place);
		recreantsService.addNewARecreants(recreants);
		List<Recreants> recreantsList = recreantsService.findAllRecreants();
		model.addAttribute("recreantsList", recreantsList);
		return "recreants";
	}

	@PostMapping("findrecreantsbyid")
	public String findRecreantsById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<Recreants> recreantsList = recreantsService.getRecreantsById(findbyid);
			model.addAttribute("recreantsList", recreantsList);
		} else {
			List<Recreants> recreantsList = recreantsService.findAllRecreants();
			model.addAttribute("recreantsList", recreantsList);
		}
		return "recreants";
	}
}
