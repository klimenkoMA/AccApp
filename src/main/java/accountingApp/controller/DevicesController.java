package accountingApp.controller;

import accountingApp.entity.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.DevicesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DevicesController {
    @Autowired
    DevicesService devicesService;
//	@Autowired
//	RecreantsService recreantsService;
//	@Autowired
//	RoomService roomService;

    @GetMapping("/devices") //allProceduresAssigned
    public String getDevices(Model model) {
        List<Devices> devicesList = devicesService.findAllDevices();
        model.addAttribute("devicesList", devicesList);
        return "devices";
    }

    @PostMapping("/adddevice")
    public String addDevice(@RequestParam String name,
                            Model model) {
        Devices devices = new Devices(name);
        devicesService.addNewDevice(devices);
        List<Devices> devicesList = devicesService.findAllDevices();
        model.addAttribute("devicesList", devicesList);
        return "devices";
    }

	@PostMapping("/deletedevice")//deleteproceduresassigned
	public String deleteDeviceById(@RequestParam Integer id,
                                   Model model) {
		devicesService.deleteDeviceById(id);
		List<Devices> devicesList = devicesService.findAllDevices();
		model.addAttribute("devicesList", devicesList);
		return "devices";
	}

//	@PostMapping("/updateproceduresassigned")
//	public String updateProceduresAssigned(@RequestParam int id,
//	                                       @RequestParam int nAssigned,
//	                                       @RequestParam int nPassed,
//	                                       @RequestParam String name,
//	                                       @RequestParam int waypaperSnnId,
//	                                       @RequestParam int cProcId,
//	                                       Model model) {
//		List<Recreants> recreantsList = recreantsService.getRecreantsById(waypaperSnnId);
//		List<Room> roomList = roomService.getProcedCaptionsById(cProcId);
//		Devices devices = new Devices(id, nAssigned, nPassed, name,
//				recreantsList.get(0), roomList.get(0));
//		devicesService.addNewProceduresAssigned(devices);
//		List<Devices> devicesList = devicesService.findAllProceduresAssigned();
//		model.addAttribute("devicesList", devicesList);
//		return "devices";
//	}

//	@PostMapping("findproceduresassignedbyid")
//	public String findProceduresAssignedById(@RequestParam int findbyid, Model model) {
//
//		if(findbyid > 0) {
//			List<Devices> devicesList = devicesService.getProceduresAssignedById(findbyid);
//			model.addAttribute("devicesList", devicesList);
//		} else {
//			List<Devices> devicesList = devicesService.findAllProceduresAssigned();
//			model.addAttribute("devicesList", devicesList);
//		}
//		return "devices";
//	}
}
