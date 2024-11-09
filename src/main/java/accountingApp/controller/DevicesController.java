package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.service.DevicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final Logger logger = LoggerFactory.getLogger(DevicesController.class);

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
            logger.warn("*** DevicesController.addDevice():" +
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
            logger.error("*** DevicesController.addDevice(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @PostMapping("/deletedevice")
    public String deleteDevice(@RequestParam String id,
                               Model model) {

        if (id == null
        ) {
            logger.warn("*** DevicesController.deleteDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                logger.warn("*** DevicesController.deleteDevice(): dborn <<<< 0 ***");
                return getDevices(model);
            }
            devicesService.deleteDeviceById(idCheck);
            return getDevices(model);
        } catch (Exception e) {
            logger.error("*** DevicesController.deleteDevice(): wrong DB's values! *** "
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
            logger.warn("*** DevicesController.updateDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            String nameWithoutSpaces = name.trim();
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                logger.warn("*** DevicesController.updateDevice(): dborn <<<< 0 ***");
                return getDevices(model);
            }
            if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
                Devices devices = new Devices(idCheck, name);
                devicesService.updateDevice(devices);
                return getDevices(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            logger.error("*** DevicesController.updateDevice(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @PostMapping("/finddevicebyname")
    public String findDevicesById(@RequestParam String name,
                                  Model model) {

        if (name == null
        ) {
            logger.warn("*** DevicesController.findDevicesById():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            int idCheck = Integer.parseInt(name);
            if (idCheck <= 0) {
                logger.warn("*** DevicesController.findDevicesById(): dborn <<<< 0 ***");
                return getDevices(model);
            } else {
                logger.debug("*** DevicesController.findDevicesById():" +
                        "FOUND DEVICE BY ID ***");
                List<Devices> devicesList;
                devicesList = devicesService.getDevicesById(idCheck);
                model.addAttribute("devicesList", devicesList);
                return "devices";
            }
        } catch (Exception e) {
            try {
                List<Devices> devicesList = devicesService.getDevicesByName(name);
                model.addAttribute("devicesList", devicesList);
                logger.debug("*** DevicesController.findDevicesById():" +
                        " FOUND DEVICE BY NAME *** " + e.getMessage());
                return "devices";
            } catch (Exception e1) {
                logger.error("*** DevicesController.findDevicesById(): wrong DB's values! *** "
                        + e1.getMessage());
                return getDevices(model);
            }
        }
    }
}
