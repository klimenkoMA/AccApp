package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Класс для переадресации введенных пользователем данных в БД, получение ответа
 * из БД, передача ответа для отображения на веб-странице
 */
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
        String nameWithoutSpaces = name.trim();
        if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
            Devices devices = new Devices(name);
            devicesService.addNewDevice(devices);
            List<Devices> devicesList = devicesService.findAllDevices();
            model.addAttribute("devicesList", devicesList);
        }
        return "devices";
    }

    @PostMapping("/deletedevice")
    public String deleteDevice(@RequestParam String id,
                               Model model) {
        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                return "devices";
            }
            devicesService.deleteDeviceById(idCheck);
            List<Devices> devicesList = devicesService.findAllDevices();
            model.addAttribute("devicesList", devicesList);
            return "devices";
        } catch (Exception e) {
            return "devices";
        }
    }

    @PostMapping("/updatedevice")
    public String updateProceduresAssigned(@RequestParam String id,
                                           @RequestParam String name,
                                           Model model) {

        try {
            String nameWithoutSpaces = name.trim();
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0 ) {
                return "devices";
            }
            if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
                Devices devices = new Devices(idCheck, name);
                devicesService.updateDevice(devices);
                List<Devices> devicesList = devicesService.findAllDevices();
                model.addAttribute("devicesList", devicesList);
            }
        } catch (Exception e) {
            return "devices";
        }
        return "devices";
    }

    @PostMapping("/finddevicebyname")
    public String findDevicesById(@RequestParam String name,
                                  Model model) {
        try {
            int idCheck = Integer.parseInt(name);
            if (idCheck <= 0) {
                System.out.println("***SUB ZERO***");
                List<Devices> devicesList = devicesService.findAllDevices();
                model.addAttribute("devicesList", devicesList);
                return "devices";
            } else {
                System.out.println("*** FIND BY ID ***");
                List<Devices> devicesList;
                devicesList = devicesService.getDevicesById(idCheck);
                model.addAttribute("devicesList", devicesList);
            }
            return "devices";
        } catch (Exception e) {
            List<Devices> devicesList = devicesService.getDevicesByName(name);
            model.addAttribute("devicesList", devicesList);
            System.out.println("*** FIND BY NAME ***");
            return "devices";
        }
    }
}
