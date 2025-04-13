package accountingApp.controller;

import accountingApp.entity.*;
import accountingApp.entity.dto.devicesdto.MaxOwnerCountDTO;
import accountingApp.service.*;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Класс для переадресации введенных пользователем данных в БД, получение ответа
 * из БД, передача ответа для отображения на веб-странице
 */
@RequestMapping
@Controller
public class DevicesController {

    final Logger logger = LoggerFactory.getLogger(DevicesController.class);
    private static final DeviceCategory[] DEVICE_CATEGORIES = DeviceCategory.values();

    @Autowired
    private DevicesService devicesService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ITStaffService itStaffService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private Checker checker;

    @GetMapping("/devices")
    public String getDevices(Model model) {
        List<Devices> devicesList = devicesService.findAllDevices();
        List<Room> roomList = roomService.findAllRoom();
        List<Employee> employeeList = employeeService.getListEmployee();
        List<ITStaff> itStaffList = itStaffService.getAllItStaff();

        List<String> categoryList = Arrays.stream(DEVICE_CATEGORIES)
                .map(DeviceCategory::getCategory)
                .collect(Collectors.toList());

        model.addAttribute("devicesList", devicesList);
        model.addAttribute("roomList", roomList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("itStaffList", itStaffList);
        model.addAttribute("categoryList", categoryList);
        return "devices";
    }

    @PostMapping("/adddevices")
    public String addDevice(@RequestParam(required = false) String category,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String inventory,
                            @RequestParam String serial,
                            @RequestParam(required = false) Room room,
                            @RequestParam(required = false) Employee employee,
                            @RequestParam(required = false) ITStaff itstaff,
                            Model model) {

        if (checker.checkAttribute(category)
                || checker.checkAttribute(name)
                || checker.checkAttribute(description)
                || checker.checkAttribute(inventory)
                || checker.checkAttribute(serial)
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
        String serialWithoutSpaces = serial.trim();

        try {
            long inventoryCheck = Long.parseLong(inventoryWithoutSpaces);
            if (!checker.checkAttribute(categoryWithoutSpaces)
                    && !checker.checkAttribute(nameWithoutSpaces)
                    && !checker.checkAttribute(descriptionWithoutSpaces)
                    && !checker.checkAttribute(inventoryWithoutSpaces)
                    && !checker.checkAttribute(serialWithoutSpaces)
            ) {

                DeviceCategory deviceCategory = Arrays.stream(DEVICE_CATEGORIES)
                        .filter(cat -> cat.getCategory().equals(categoryWithoutSpaces))
                        .findFirst()
                        .orElse(DeviceCategory.Компьютер);

                Devices devices = new Devices(deviceCategory
                        , nameWithoutSpaces
                        , descriptionWithoutSpaces
                        , inventoryCheck
                        , serialWithoutSpaces
                        , room
                        , employee
                        , itstaff);
                devicesService.addNewDevice(devices);

                List<Devices> devicesList = devicesService.findAllDevices();
                Devices dev = new Devices();

                for (Devices dv : devicesList
                ) {
                    if (dv.getName().equals(nameWithoutSpaces)
                            && dv.getCategory().getCategory().equals(deviceCategory.getCategory())
                            && dv.getSerial().equals(serialWithoutSpaces)
                            && dv.getRoom().equals(room)
                            && dv.getItstaff().equals(itstaff)
                            && dv.getEmployee().equals(employee)
                            && dv.getInventory() == inventoryCheck
                            && dv.getDescription().equals(descriptionWithoutSpaces)) {
                        dev = dv;
                        break;
                    }
                }

                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                Repair repair = new Repair(currentDate.format(formatter), dev);
                repairService.createRepair(repair);
                dev.setRepair(repair);
                devicesService.addNewDevice(dev);
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

            Repair repair = device.getRepair();
            device.setRepair(null);
            repairService.deleteRepair(repair.getId());

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
    public String updateDevice(@RequestParam String id,
                               @RequestParam(required = false) String category,
                               @RequestParam String name,
                               @RequestParam String description,
                               @RequestParam String inventory,
                               @RequestParam String serial,
                               @RequestParam(required = false) Room room,
                               @RequestParam(required = false) Employee employee,
                               @RequestParam(required = false) ITStaff itstaff,
                               Model model) {

        if (checker.checkAttribute(category)
                || checker.checkAttribute(id)
                || checker.checkAttribute(name)
                || checker.checkAttribute(description)
                || checker.checkAttribute(inventory)
                || checker.checkAttribute(serial)
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
            String serialWithoutSpaces = serial.trim();

            if (idCheck <= 0
                    || checker.checkAttribute(idCheck + "")
                    || inventoryCheck <= 0
                    || checker.checkAttribute(inventoryCheck + "")
                    || checker.checkAttribute(serialWithoutSpaces)
            ) {
                logger.warn("*** DevicesController.updateDevice(): dborn <<<< 0 ***");
                return getDevices(model);
            }

            if (!checker.checkAttribute(categoryWithoutSpaces)
                    && !checker.checkAttribute(nameWithoutSpaces)
                    && !checker.checkAttribute(descriptionWithoutSpaces)
            ) {

                DeviceCategory deviceCategory = Arrays.stream(DEVICE_CATEGORIES)
                        .filter(cat -> cat.getCategory().equals(categoryWithoutSpaces))
                        .findFirst()
                        .orElse(DeviceCategory.Компьютер);

                Repair repair = devicesService.getDevicesById(idCheck).get(0).getRepair();

                Devices devices = new Devices(idCheck
                        , deviceCategory
                        , nameWithoutSpaces
                        , descriptionWithoutSpaces
                        , inventoryCheck
                        , serialWithoutSpaces
                        , room
                        , employee
                        , itstaff);
                devices.setRepair(repair);
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

    @PostMapping("/filterdevicesbycategory")
    public String findDevicesListByCategory(@RequestParam String category
            , Model model) {

        if (checker.checkAttribute(category)) {
            logger.warn("*** DevicesController.findDevicesListByCategory():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }
        String categoryWithoutSpaces = category.trim();

        try {

            DeviceCategory deviceCategory = Arrays.stream(DEVICE_CATEGORIES)
                    .filter(cat -> cat.getCategory().equals(categoryWithoutSpaces))
                    .findFirst()
                    .orElse(DeviceCategory.Компьютер);

            List<Devices> devicesList = devicesService.getDevicesByCategory(deviceCategory);
            model.addAttribute("devicesList", devicesList);

            return "devices";
        } catch (Exception e) {
            logger.error("*** DevicesController.findDevicesListByCategory(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @PostMapping("/filterdevicesbyattrs")
    public String findDevicesListByAttrs(@RequestParam String attrs
            , Model model) {

        if (checker.checkAttribute(attrs)) {
            logger.warn("*** DevicesController.findDevicesListByAttrs():" +
                    "  Attribute has a null value! ***");
            return getDevices(model);
        }
        String attrsWithoutSpaces = attrs.trim().toLowerCase(Locale.ROOT);

        try {
            List<Devices> devices = devicesService.findAllDevices();
            List<Devices> devicesList = new ArrayList<>();

            for (Devices dev : devices
            ) {
                if ((dev.getId() + "").contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if (dev.getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if (dev.getCategory().getCategory().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if (dev.getDescription().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if (dev.getEmployee().getFio().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if ((dev.getInventory() + "").toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if (dev.getItstaff().getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if ((dev.getRepair().getDurability() + "").toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if (dev.getRoom().getNumber().contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                } else if (dev.getSerial().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    devicesList.add(dev);
                }
            }

            model.addAttribute("devicesList", devicesList);
            if (devicesList.isEmpty()) {
                logger.debug("*** DevicesController.findDevicesListByAttrs():  DATA NOT FOUND IN DB***");
                return getDevices(model);
            }
            return "devices";
        } catch (Exception e) {
            logger.error("*** DevicesController.findDevicesListByAttrs(): wrong DB's values! *** "
                    + e.getMessage());
            return getDevices(model);
        }
    }

    @GetMapping("/maxownercountreport")
    public String maxOwnerCountReport(Model model) {

        List<MaxOwnerCountDTO> dtoList = devicesService.getOwnersCount();

        String[] owners = dtoList.stream()
                .map(MaxOwnerCountDTO::getOwner)
                .toArray(String[]::new);

        long[] counts = dtoList.stream()
                .mapToLong(MaxOwnerCountDTO::getDevicesCount)
                .toArray();

        model.addAttribute("dtoList", dtoList);
        model.addAttribute("owners", owners);
        model.addAttribute("counts", counts);

        return "/reports/devicesreports/reportmaxownercount";
    }
}
