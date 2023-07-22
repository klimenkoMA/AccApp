package accountingApp.controller;

import accountingApp.entity.ITStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.ITStaffService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ITStaffController {

	@Autowired
	ITStaffService ITStaffService;

	@GetMapping("/itstaff") //allPersonsProf
	public String getItStaff(Model model) {
		List<ITStaff> ITStaffList = ITStaffService.getAllItStaff();
		model.addAttribute("itStaffList", ITStaffList);
		return "itstaff";
	}

	@PostMapping("/additstaff")//addpersonsprof
	public String addItStaff(@RequestParam String name,
							 Model model) {
		ITStaff ITStaff = new ITStaff(name);
		ITStaffService.addNewItStaff(ITStaff);
		List<ITStaff> ITStaffList = ITStaffService.getAllItStaff();
		model.addAttribute("itStaffList", ITStaffList);
		return "itstaff";
	}

//	@PostMapping("/deletepersonsprof")
//	public String deletePersonsProf(@RequestParam int id, Model model) {
//		ITStaffService.deletePersonsProfById(id);
//		List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
//		model.addAttribute("itStaffList", ITStaffList);
//		return "itstaff";
//	}

//	@PostMapping("/updatepersonsprof")
//	public String updatePersonProf(@RequestParam int id, @RequestParam String name, Model model) {
//		ITStaff ITStaff = new ITStaff(id, name);
//		ITStaffService.updatePersonsProf(ITStaff);
//		List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
//		model.addAttribute("itStaffList", ITStaffList);
//		return "itstaff";
//	}

//	@PostMapping("findpersonsprofbyid")
//	public String findPersonById(@RequestParam int findbyid, Model model) {
//
//		if(findbyid > 0) {
//			List<ITStaff> ITStaffList = ITStaffService.getPersonsProfById(findbyid);
//			model.addAttribute("itStaffList", ITStaffList);
//		} else {
//			List<ITStaff> ITStaffList = ITStaffService.getAllPersonsProf();
//			model.addAttribute("itStaffList", ITStaffList);
//		}
//		return "itstaff";
//	}


}
