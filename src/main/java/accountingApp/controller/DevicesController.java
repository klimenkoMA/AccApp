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

        if (name == null
        ) {
            System.out.println("*** DevicesController.addDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        String nameWithoutSpaces = name.trim();
        try {
            if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
                Devices devices = new Devices(name);
                devicesService.addNewDevice(devices);
                return getDevices(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            System.out.println("*** DevicesController.addDevice(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @PostMapping("/deletedevice")
    public String deleteDevice(@RequestParam String id,
                               Model model) {

        if (id == null
        ) {
            System.out.println("*** DevicesController.deleteDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                System.out.println("*** DevicesController.deleteDevice(): dborn <<<< 0 ***");
                return getDevices(model);
            }
            devicesService.deleteDeviceById(idCheck);
            return getDevices(model);
        } catch (Exception e) {
            System.out.println("*** DevicesController.deleteDevice(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @PostMapping("/updatedevice")
    public String updateDevice(@RequestParam String id,
                               @RequestParam String name,
                               Model model) {

        if (id == null
                || name == null
        ) {
            System.out.println("*** DevicesController.updateDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            String nameWithoutSpaces = name.trim();
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                System.out.println("*** DevicesController.updateDevice(): dborn <<<< 0 ***");
                return getDevices(model);
            }
            if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
                Devices devices = new Devices(idCheck, name);
                devicesService.updateDevice(devices);
                return getDevices(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            System.out.println("*** DevicesController.updateDevice(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @PostMapping("/finddevicebyname")
    public String findDevicesById(@RequestParam String name,
                                  Model model) {

        if (name == null
        ) {
            System.out.println("*** DevicesController.findDevicesById():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            int idCheck = Integer.parseInt(name);
            if (idCheck <= 0) {
                System.out.println("*** DevicesController.findDevicesById(): dborn <<<< 0 ***");
                return getDevices(model);
            } else {
                System.out.println("*** DevicesController.findDevicesById():" +
                        "FOUND DEVICE BY ID ***");
                List<Devices> devicesList;
                devicesList = devicesService.getDevicesById(idCheck);
                model.addAttribute("devicesList", devicesList);
                return "devices";
            }
        } catch (Exception e) {
            List<Devices> devicesList = devicesService.getDevicesByName(name);
            model.addAttribute("devicesList", devicesList);
            System.out.println("*** DevicesController.findDevicesById():" +
                    " FOUND DEVICE BY NAME *** " + e.getMessage());
            return "devices";
        }
    }
}
