package accountingApp.controller;

import accountingApp.entity.*;
import accountingApp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventsController {

    final Logger logger = LoggerFactory.getLogger(EventsController.class);

    @Autowired
    EventsService eventsService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ITStaffService itStaffService;
    @Autowired
    DevicesService devicesService;
    @Autowired
    WorkAreaService workAreaService;

    @GetMapping("/events")
    public String getEvents(Model model) {
        List<Events> eventsList = eventsService.findAllEvents();
        List<Employee> employeeList = employeeService.getListEmployee();
        List<ITStaff> itStaffList = itStaffService.getAllItStaff();
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        List<Devices> devicesList = devicesService.findAllDevices();
        model.addAttribute("eventsList", eventsList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("itStaffList", itStaffList);
        model.addAttribute("workAreaList", workAreaList);
        model.addAttribute("devicesList", devicesList);
        return "events";
    }

    @PostMapping("/addevent")
    public String addNewEvent(@RequestParam String date,
                              @RequestParam Devices device,
                              @RequestParam Employee employee,
                              @RequestParam ITStaff itstaff,
                              @RequestParam WorkArea workarea,
                              @RequestParam String comment,
                              Model model) {

        if (date == null
                || device == null
                || employee == null
                || itstaff == null
                || workarea == null
                || comment == null
        ) {
            logger.warn("*** EventsController.addEvent():  Attribute has a null value! ***");
            return getEvents(model);
        }

        String dateWithoutSpaces = date.trim();
        String deviceWithoutSpaces = device.getName();
        String employeeIdWithoutSpaces = employee.getFio();
        String itstaffIdWithoutSpaces = itstaff.getName();
        String workareaWithoutSpaces = workarea.getName();
        String commentWithoutSpaces = comment.trim();
        try {
           if (!dateWithoutSpaces.equals("")
                    && !deviceWithoutSpaces.equals("")
                    && !employeeIdWithoutSpaces.equals("")
                    && !workareaWithoutSpaces.equals("")
                    && !itstaffIdWithoutSpaces.equals("")) {

                Events events = new Events(dateWithoutSpaces,
                        device,
                        employee,
                        itstaff,
                        workarea,
                        commentWithoutSpaces);
                eventsService.addNewEvent(events);
            }
            return getEvents(model);
        } catch (Exception e) {
            logger.error("*** EventsController.addNewEvent():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getEvents(model);
        }
    }

    @PostMapping("/deleteevent")
    public String deleteEvent(@RequestParam String id,
                              Model model) {
        if (id == null
        ) {
            logger.warn("*** EventsController.deleteEvent():  Attribute ID has a null value! ***");
            return getEvents(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                logger.warn("*** EventsController.addNewEvent(): ID is SUBZERO***");
            } else {
                eventsService.deleteEventsById(idCheck);
            }
            return getEvents(model);
        } catch (Exception e) {
            logger.error("*** EventsController.deleteEvent():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getEvents(model);
        }
    }

    @PostMapping("/updateevent")
    public String updateEvent(@RequestParam String id,
                              @RequestParam String date,
                              @RequestParam Devices device,
                              @RequestParam Employee employee,
                              @RequestParam ITStaff itstaff,
                              @RequestParam WorkArea workarea,
                              @RequestParam String comment,
                              Model model) {

        if (id == null
                || date == null
                || device == null
                || employee == null
                || itstaff == null
                || workarea == null
                || comment == null
        ) {
            logger.warn("*** EventsController.updateEvent():  Attribute has a null value! ***");
            return getEvents(model);
        }

        String idWithoutSpaces = id.trim();
        String dateWithoutSpaces = date.trim();
        String deviceWithoutSpaces = device.getName();
        String employeeIdWithoutSpaces = employee.getFio();
        String itstaffIdWithoutSpaces = itstaff.getName();
        String workareaWithoutSpaces = workarea.getName();
        String commentWithoutSpaces = comment.trim();

        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** EventsController.updateEvent():  WRONG ID FORMAT***");
                return getEvents(model);
            } else if (!dateWithoutSpaces.equals("")
                    && !deviceWithoutSpaces.equals("")
                    && !employeeIdWithoutSpaces.equals("")
                    && !workareaWithoutSpaces.equals("")
                    && !itstaffIdWithoutSpaces.equals("")) {

                Events events = new Events(idCheck,
                        dateWithoutSpaces,
                        device,
                        employee,
                        itstaff,
                        workarea,
                        commentWithoutSpaces);
                eventsService.updateEvent(events);
            }
            return getEvents(model);

        } catch (Exception e) {
            logger.error("*** EventsController.updateEvent():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getEvents(model);
        }
    }

    @PostMapping("/findeventbyid")
    public String findEventsById(@RequestParam String id,
                                 Model model) {
        if (id == null
        ) {
            logger.warn("*** EventsController.findEventsById():  Attribute ID has a null value! ***");
            return getEvents(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** EventsController.fidEventById(): ID is SUBZERO***");
                return getEvents(model);
            } else {
                List<Events> eventsList = eventsService.getEventById(idCheck);
                model.addAttribute("eventsList", eventsList);
                return "events";
            }
        } catch (Exception e) {
            logger.error("*** EventsController.fidEventById():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getEvents(model);
        }
    }
}
