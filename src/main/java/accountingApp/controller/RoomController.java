package accountingApp.controller;

import accountingApp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.service.RoomService;

import java.util.List;

@Controller
public class RoomController {
	@Autowired
	RoomService roomService;

	@GetMapping("/allProcedCaptions")
	public String getProcedCaptions(Model model) {
		List<Room> roomList = roomService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", roomList);
		return "room";
	}

	@PostMapping("/addprocedcaptions")
	public String addProcedCaptions(@RequestParam String caption,
	                                Model model) {
		Room room = new Room(caption);
		roomService.addNewProcedCaptions(room);
		List<Room> roomList = roomService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", roomList);
		return "room";
	}

	@PostMapping("/deleteprocedcaptions")
	public String deleteProcedCaptions(@RequestParam int cProc, Model model) {
		roomService.deleteProcedCaptionsById(cProc);
		List<Room> roomList = roomService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", roomList);
		return "room";
	}

	@PostMapping("/updateprocedcaptions")
	public String updateProcedCaptions(@RequestParam int cProc, @RequestParam String caption, Model model) {
		Room room = new Room(cProc, caption);
		roomService.updateProcedCaptions(room);
		List<Room> roomList = roomService.findAllProcedCaptions();
		model.addAttribute("procedCaptionsList", roomList);
		return "room";
	}

	@PostMapping("findprocedcaptionsbyid")
	public String findProcedCaptionsById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<Room> roomList = roomService.getProcedCaptionsById(findbyid);
			model.addAttribute("procedCaptionsList", roomList);
		} else {
			List<Room> roomList = roomService.findAllProcedCaptions();
			model.addAttribute("procedCaptionsList", roomList);
		}
		return "room";
	}
}
