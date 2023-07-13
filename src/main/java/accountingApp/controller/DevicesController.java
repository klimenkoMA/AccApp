package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.service.RoomService;
import accountingApp.service.DevicesService;

import java.util.List;

@Controller
public class DevicesController {
	@Autowired
	DevicesService devicesService;
//	@Autowired
//	RecreantsService recreantsService;
//	@Autowired
//	RoomService roomService;

	@GetMapping("/allProceduresAssigned")
	public String getProceduresAssigned(Model model) {
		List<Devices> devicesList = devicesService.findAllProceduresAssigned();
		model.addAttribute("proceduresAssignedList", devicesList);
		return "devices";
	}

//	@PostMapping("/addproceduresassigned")
//	public String addProceduresAssigned(@RequestParam int nAssigned,
//	                                    @RequestParam int nPassed,
//	                                    @RequestParam String comment,
//	                                    @RequestParam int waypaperSnnId,
//	                                    @RequestParam int cProcId,
//	                                    Model model) {
//		List<Recreants> recreantsList = recreantsService.getRecreantsById(waypaperSnnId);
//		List<Room> roomList = roomService.getProcedCaptionsById(cProcId);
//		Devices devices = new Devices(nAssigned, nPassed, comment,
//				recreantsList.get(0), roomList.get(0));
//		devicesService.addNewProceduresAssigned(devices);
//		List<Devices> devicesList = devicesService.findAllProceduresAssigned();
//		model.addAttribute("proceduresAssignedList", devicesList);
//		return "devices";
//	}

	@PostMapping("/deleteproceduresassigned")
	public String deleteProceduresAssigned(@RequestParam int id,
	                                       Model model) {
		devicesService.deleteProceduresAssignedById(id);
		List<Devices> devicesList = devicesService.findAllProceduresAssigned();
		model.addAttribute("proceduresAssignedList", devicesList);
		return "devices";
	}

//	@PostMapping("/updateproceduresassigned")
//	public String updateProceduresAssigned(@RequestParam int id,
//	                                       @RequestParam int nAssigned,
//	                                       @RequestParam int nPassed,
//	                                       @RequestParam String comment,
//	                                       @RequestParam int waypaperSnnId,
//	                                       @RequestParam int cProcId,
//	                                       Model model) {
//		List<Recreants> recreantsList = recreantsService.getRecreantsById(waypaperSnnId);
//		List<Room> roomList = roomService.getProcedCaptionsById(cProcId);
//		Devices devices = new Devices(id, nAssigned, nPassed, comment,
//				recreantsList.get(0), roomList.get(0));
//		devicesService.addNewProceduresAssigned(devices);
//		List<Devices> devicesList = devicesService.findAllProceduresAssigned();
//		model.addAttribute("proceduresAssignedList", devicesList);
//		return "devices";
//	}

	@PostMapping("findproceduresassignedbyid")
	public String findProceduresAssignedById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<Devices> devicesList = devicesService.getProceduresAssignedById(findbyid);
			model.addAttribute("proceduresAssignedList", devicesList);
		} else {
			List<Devices> devicesList = devicesService.findAllProceduresAssigned();
			model.addAttribute("proceduresAssignedList", devicesList);
		}
		return "devices";
	}
}
