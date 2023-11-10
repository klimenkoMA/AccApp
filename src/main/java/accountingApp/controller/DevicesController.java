package accountingApp.controller;

import accountingApp.entity.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.DevicesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping
@Controller
public class DevicesController {
    @Autowired
    DevicesService devicesService;

    @GetMapping("/devices")
    public String getDevices(Model model) {
        List<Devices> devicesList = devicesService.findAllDevices();
        model.addAttribute("devicesList", devicesList);
        return "devices";
    }

    @PostMapping("/adddevice")
    public String addDevice(@RequestParam String name,
                            Model model) {
        if (name != null && !name.equals("") && !name.equals(" ")) {
            Devices devices = new Devices(name);
            devicesService.addNewDevice(devices);
            List<Devices> devicesList = devicesService.findAllDevices();
            model.addAttribute("devicesList", devicesList);
        }
        return "devices";
    }

    @PostMapping("/deletedevice")
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

    @PostMapping("/finddevicebyid")
    public String findDevicesById(@RequestParam int id,
                                  Model model) {
        List<Devices> devicesList;
        if (id > 0) {
            devicesList = devicesService.getDevicesById(id);
        } else {
            devicesList = devicesService.findAllDevices();
        }
        model.addAttribute("devicesList", devicesList);
        return "devices";
    }

    @PostMapping("/finddevicebyname")
    public String findDevicesById(@RequestParam String name,
                                  Model model) {
        List<Devices> devicesList = devicesService.getDevicesByName(name);
        model.addAttribute("devicesList", devicesList);
        return "devices";
    }
}
