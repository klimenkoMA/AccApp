package accountingApp.controller;

import accountingApp.entity.*;
import accountingApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventsController {
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
            System.out.println("*** EventsController.addEvent:  Attribute has a null value! ***");
            return this.getEvents(model);
        }

        String dateWithoutSpaces = date.trim();
        String deviceWithoutSpaces = device.getName();
        String employeeIdWithoutSpaces = employee.getFio();
        String itstaffIdWithoutSpaces = itstaff.getName();
        String workareaWithoutSpaces = workarea.getName();
        String commentWithoutSpaces = comment.trim();
        try {
            int dateCheck = Integer.parseInt(dateWithoutSpaces);
            if (dateCheck <= 0 || date.length() < 6) {
                System.out.println("*** EventsController.addNewEvent:  WRONG DATE FORMAT***");
            } else if (!dateWithoutSpaces.equals("")
                    && !deviceWithoutSpaces.equals("")
                    && !employeeIdWithoutSpaces.equals("")
                    && !workareaWithoutSpaces.equals("")
                    && !itstaffIdWithoutSpaces.equals("")) {

                Events events = new Events(date,
                        device,
                        employee,
                        itstaff,
                        workarea,
                        commentWithoutSpaces);
                eventsService.addNewEvent(events);
                List<Events> eventsList = eventsService.findAllEvents();
                model.addAttribute("eventsList", eventsList);
            }
            return this.getEvents(model);
        } catch (Exception e) {
            System.out.println("*** EventsController.addNewEvent:  WRONG DB VALUES*** "
                    + e.getMessage());
            return this.getEvents(model);
        }
    }

    @PostMapping("/deleteevent")
    public String deleteEvent(@RequestParam String id,
                              Model model) {
        if (id == null
        ) {
            System.out.println("*** EventsController.deleteEvent:  Attribute ID has a null value! ***");
            return this.getEvents(model);
        }

        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                System.out.println("*** EventsController.addNewEvent: ID is SUBZERO***");
            } else {
                eventsService.deleteEventsById(idCheck);
                List<Events> eventsList = eventsService.findAllEvents();
                model.addAttribute("eventsList", eventsList);
            }
            return this.getEvents(model);
        } catch (Exception e) {
            System.out.println("*** EventsController.deleteEvent:  WRONG DB VALUES*** "
                    + e.getMessage());
            return this.getEvents(model);
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
            System.out.println("*** EventsController.updateEvent:  Attribute has a null value! ***");
            return this.getEvents(model);
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
            int dateCheck = Integer.parseInt(dateWithoutSpaces);
            if (dateCheck <= 0 || date.length() < 6 || idCheck <= 0) {
                System.out.println("*** EventsController.updateEvent:  WRONG DATE FORMAT or ID FORMAT***");
                return this.getEvents(model);
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
                List<Events> eventsList = eventsService.findAllEvents();
                model.addAttribute("eventsList", eventsList);
            }
            return this.getEvents(model);

        } catch (Exception e) {
            System.out.println("*** EventsController.updateEvent:  WRONG DB VALUES*** "
                    + e.getMessage());
            getEvents(model);
            return getEvents(model);
        }
    }

    @PostMapping("/findeventbyid")
    public String findEventsById(@RequestParam String id,
                                 Model model) {
        if (id == null
        ) {
            System.out.println("*** EventsController.findEventsById:  Attribute ID has a null value! ***");
            return this.getEvents(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** EventsController.fidEventById: ID is SUBZERO***");
                this.getEvents(model);
            } else {
                List<Events> eventsList;
                eventsList = eventsService.getEventById(idCheck);
                model.addAttribute("eventsList", eventsList);
            }
            return "events";
        } catch (Exception e) {
            System.out.println("*** EventsController.fidEventById:  WRONG DB VALUES*** "
                    + e.getMessage());
            return this.getEvents(model);
        }

    }
}
