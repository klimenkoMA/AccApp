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
    public String deleteDevice(@RequestParam Integer id,
                               Model model) {
        devicesService.deleteDeviceById(id);
        List<Devices> devicesList = devicesService.findAllDevices();
        model.addAttribute("devicesList", devicesList);
        return "devices";
    }

    @PostMapping("/updatedevice")
    public String updateProceduresAssigned(@RequestParam int id,
                                           @RequestParam String name,
                                           Model model) {
        Devices devices = new Devices(id, name);
        devicesService.updateDevice(devices);
        List<Devices> devicesList = devicesService.findAllDevices();
        model.addAttribute("devicesList", devicesList);
        return "devices";
    }

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
