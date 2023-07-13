package accountingApp.controller;

import accountingApp.entity.ITStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.service.ITStaffService;

import java.util.List;

@Controller
public class ITStaffController {

	@Autowired
	ITStaffService ITStaffService;

//	@PostMapping("/addpersonsprof")
//	public String addPersonsProf(@RequestParam String name_rec, Model model) {
//		ITStaff ITStaff = new ITStaff(name_rec);
//		ITStaffService.addNewPersonsProf(ITStaff);
//		List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
//		model.addAttribute("personsProfList", ITStaffList);
//		return "itstaff";
//	}

	@PostMapping("/deletepersonsprof")
	public String deletePersonsProf(@RequestParam int id, Model model) {
		ITStaffService.deletePersonsProfById(id);
		List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
		model.addAttribute("personsProfList", ITStaffList);
		return "itstaff";
	}

//	@PostMapping("/updatepersonsprof")
//	public String updatePersonProf(@RequestParam int id, @RequestParam String name_rec, Model model) {
//		ITStaff ITStaff = new ITStaff(id, name_rec);
//		ITStaffService.updatePersonsProf(ITStaff);
//		List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
//		model.addAttribute("personsProfList", ITStaffList);
//		return "itstaff";
//	}

	@PostMapping("findpersonsprofbyid")
	public String findPersonById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<ITStaff> ITStaffList = ITStaffService.getPersonsProfById(findbyid);
			model.addAttribute("personsProfList", ITStaffList);
		} else {
			List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
			model.addAttribute("personsProfList", ITStaffList);
		}
		return "itstaff";
	}

	@GetMapping("/allPersonsProf")
	public String getPersonsProf(Model model) {
		List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
		model.addAttribute("personsProfList", ITStaffList);
		return "itstaff";
	}
}
