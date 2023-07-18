package accountingApp.controller;

import accountingApp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {
	@Autowired
	RoomService roomService;

	@GetMapping("/room") //allProcedCaptions
	public String getRoom(Model model) {
		List<Room> roomList = roomService.findAllRoom();
		model.addAttribute("roomList", roomList);
		return "room";
	}

	@PostMapping("/addroom")//addprocedcaptions
	public String addRoom(@RequestParam String number,
						  Model model) {
		Room room = new Room(number);
		roomService.addNewRoom(room);
		List<Room> roomList = roomService.findAllRoom();
		model.addAttribute("roomList", roomList);
		return "room";
	}

//	@PostMapping("/deleteprocedcaptions")
//	public String deleteProcedCaptions(@RequestParam int cProc, Model model) {
//		roomService.deleteProcedCaptionsById(cProc);
//		List<Room> roomList = roomService.findAllProcedCaptions();
//		model.addAttribute("roomList", roomList);
//		return "room";
//	}

//	@PostMapping("/updateprocedcaptions")
//	public String updateProcedCaptions(@RequestParam int cProc, @RequestParam String number, Model model) {
//		Room room = new Room(cProc, number);
//		roomService.updateProcedCaptions(room);
//		List<Room> roomList = roomService.findAllProcedCaptions();
//		model.addAttribute("roomList", roomList);
//		return "room";
//	}

//	@PostMapping("findprocedcaptionsbyid")
//	public String findProcedCaptionsById(@RequestParam int findbyid, Model model) {
//
//		if(findbyid > 0) {
//			List<Room> roomList = roomService.getProcedCaptionsById(findbyid);
//			model.addAttribute("roomList", roomList);
//		} else {
//			List<Room> roomList = roomService.findAllProcedCaptions();
//			model.addAttribute("roomList", roomList);
//		}
//		return "room";
//	}
}
