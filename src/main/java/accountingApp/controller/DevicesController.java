package accountingApp.controller;

import accountingApp.entity.*;
import accountingApp.service.DevicesService;
import accountingApp.service.EmployeeService;
import accountingApp.service.ITStaffService;
import accountingApp.service.RoomService;
import accountingApp.usefulmethods.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    private DevicesService devicesService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ITStaffService itStaffService;
    @Autowired
    private Checker checker;

    @GetMapping("/devices")
    public String getDevices(Model model) {
        List<Devices> devicesList = devicesService.findAllDevices();
        List<Room> roomList = roomService.findAllRoom();
        List<Employee> employeeList = employeeService.getListEmployee();
        List<ITStaff> itStaffList = itStaffService.getAllItStaff();

        DeviceCategory[] categoriesArray = DeviceCategory.values();
        List<String> categoryList = new ArrayList<>();

        for (DeviceCategory cat : categoriesArray
        ) {
            categoryList.add(cat.getCategory());
        }

        model.addAttribute("devicesList", devicesList);
        model.addAttribute("roomList", roomList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("itStaffList", itStaffList);
        model.addAttribute("categoryList", categoryList);
        return "devices";
    }

    @PostMapping("/adddevice")
    public String addDevice(@RequestParam(required = false) String category,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String inventory,
                            @RequestParam(required = false) Room room,
                            @RequestParam(required = false) Employee employee,
                            @RequestParam(required = false) ITStaff itstaff,
                            Model model) {

        if (checker.checkAttribute(category)
                || checker.checkAttribute(name)
                || checker.checkAttribute(description)
                || checker.checkAttribute(inventory)
                || room == null
                || employee == null
                || itstaff == null
        ) {
            logger.warn("*** DevicesController.addDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        String categoryWithoutSpaces = category.trim();
        String nameWithoutSpaces = name.trim();
        String descriptionWithoutSpaces = description.trim();
        String inventoryWithoutSpaces = inventory.trim();

        try {
            long inventoryCheck = Long.parseLong(inventoryWithoutSpaces);
            if (!checker.checkAttribute(categoryWithoutSpaces)
                    && !checker.checkAttribute(nameWithoutSpaces)
                    && !checker.checkAttribute(descriptionWithoutSpaces)
                    && !checker.checkAttribute(inventoryWithoutSpaces)
            ) {
                DeviceCategory deviceCategory = DeviceCategory.Компьютер;
                DeviceCategory[] categoriesArray = DeviceCategory.values();
                for (DeviceCategory cat : categoriesArray
                ) {
                    if (cat.getCategory().equals(categoryWithoutSpaces)) {
                        deviceCategory = cat;
                        break;
                    }
                }

                Devices devices = new Devices(deviceCategory
                        , nameWithoutSpaces
                        , descriptionWithoutSpaces
                        , inventoryCheck
                        , room
                        , employee
                        , itstaff);
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

        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** DevicesController.deleteDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0 || checker.checkAttribute(idCheck + "")) {
                logger.warn("*** DevicesController.deleteDevice(): dborn <<<< 0 ***");
                return getDevices(model);
            }

            List<Devices> devices = devicesService.getDevicesById(idCheck);
            Devices device = devices.get(0);
            device.setEmployee(null);
            device.setRoom(null);
            device.setItstaff(null);
            device.setEvents(null);
            devicesService.updateDevice(device);

            devicesService.deleteDeviceById(idCheck);
            return getDevices(model);
        } catch (Exception e) {
            logger.error("*** DevicesController.deleteDevice(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @PostMapping("/updatedevice")
    public String updateDevice(@RequestParam(required = false) String category,
                               @RequestParam String id,
                               @RequestParam String name,
                               @RequestParam String description,
                               @RequestParam String inventory,
                               @RequestParam(required = false) Room room,
                               @RequestParam(required = false) Employee employee,
                               @RequestParam(required = false) ITStaff itstaff,
                               Model model) {

        if (checker.checkAttribute(category)
                || checker.checkAttribute(id)
                || checker.checkAttribute(name)
                || checker.checkAttribute(description)
                || checker.checkAttribute(inventory)
                || room == null
                || employee == null
                || itstaff == null
        ) {
            logger.warn("*** DevicesController.updateDevice():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            String categoryWithoutSpaces = category.trim();
            String nameWithoutSpaces = name.trim();
            String descriptionWithoutSpaces = description.trim();
            String inventoryWithoutSpaces = inventory.trim();
            int idCheck = Integer.parseInt(id);
            long inventoryCheck = Long.parseLong(inventoryWithoutSpaces);

            if (idCheck <= 0
                    || checker.checkAttribute(idCheck + "")
                    || inventoryCheck <= 0
                    || checker.checkAttribute(inventoryCheck + "")
            ) {
                logger.warn("*** DevicesController.updateDevice(): dborn <<<< 0 ***");
                return getDevices(model);
            }

            if (!checker.checkAttribute(categoryWithoutSpaces)
                    && !checker.checkAttribute(nameWithoutSpaces)
                    && !checker.checkAttribute(descriptionWithoutSpaces)
            ) {
                DeviceCategory deviceCategory = DeviceCategory.Компьютер;
                DeviceCategory[] categoriesArray = DeviceCategory.values();
                for (DeviceCategory cat : categoriesArray
                ) {
                    if (cat.getCategory().equals(categoryWithoutSpaces)) {
                        deviceCategory = cat;
                        break;
                    }
                }

                Devices devices = new Devices(idCheck
                        , deviceCategory
                        , nameWithoutSpaces
                        , descriptionWithoutSpaces
                        , inventoryCheck
                        , room
                        , employee
                        , itstaff);
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

        if (checker.checkAttribute(name)
        ) {
            logger.warn("*** DevicesController.findDevicesById():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }

        try {
            int idCheck = Integer.parseInt(name);
            if (idCheck <= 0 || checker.checkAttribute(idCheck + "")) {
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
