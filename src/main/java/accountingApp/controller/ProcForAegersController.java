package accountingApp.controller;

import accountingApp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import accountingApp.entity.WorkArea;
import accountingApp.entity.ProcForAegers;
import accountingApp.service.WorkAreaService;
import accountingApp.service.ProcForAegersService;
import accountingApp.service.RoomService;

import java.util.List;

@Controller
public class ProcForAegersController {
	@Autowired
	ProcForAegersService procForAegersService;
	@Autowired
	WorkAreaService workAreaService;
	@Autowired
	RoomService roomService;

	@GetMapping("/allProcForAegers")
	public String getProcForAegers(Model model) {
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("/addprocforaegers")
	public String addProcForAegers(@RequestParam int aegerCaptionsId,
	                               @RequestParam int procedCaptionsId,
	                               Model model) {
		List<WorkArea> workAreaList = workAreaService.getAegerCaptionsById(aegerCaptionsId);
		List<Room> roomList = roomService.getProcedCaptionsById(procedCaptionsId);
		ProcForAegers procForAegers = new ProcForAegers(workAreaList.get(0), roomList.get(0));
		procForAegersService.addNewProcForAegers(procForAegers);
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("/deleteprocforaegers")
	public String deleteProcForAegers(@RequestParam int id, Model model) {
		procForAegersService.deleteProcForAegersById(id);
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("/updateprocforaegers")
	public String updateProcForAegers(@RequestParam int id,
	                                  @RequestParam int aegerCaptionsId,
	                                  @RequestParam int procedCaptionsId, Model model) {
		List<WorkArea> workAreaList = workAreaService.getAegerCaptionsById(aegerCaptionsId);
		List<Room> roomList = roomService.getProcedCaptionsById(procedCaptionsId);
		ProcForAegers procForAegers = new ProcForAegers(id, workAreaList.get(0), roomList.get(0));
		procForAegersService.updateProcForAegers(procForAegers);
		List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
		model.addAttribute("procForAegersList", procForAegersList);
		return "procforaegers";
	}

	@PostMapping("findprocforaegersbyid")
	public String findProcForAegersById(@RequestParam int findbyid, Model model) {

		if(findbyid > 0) {
			List<ProcForAegers> procForAegersList = procForAegersService.getProcForAegersById(findbyid);
			model.addAttribute("procForAegersList", procForAegersList);
		} else {
			List<ProcForAegers> procForAegersList = procForAegersService.findAllProcForAegers();
			model.addAttribute("procForAegersList", procForAegersList);
		}
		return "procforaegers";
	}
}
