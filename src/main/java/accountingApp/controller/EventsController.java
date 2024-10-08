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

    @GetMapping("/events")
    public String getEvents(Model model) {
        List<Events> eventsList = eventsService.findAllEvents();
        model.addAttribute("eventsList", eventsList);
        return "events";
    }

    @PostMapping("/addevent")
    public String addNewEvent(@RequestParam String date,
                              @RequestParam String device,
                              @RequestParam String employeeid,
                              @RequestParam String itstaffid,
                              Model model) {
        String dateWithoutSpaces = date.trim();
        String deviceWithoutSpaces = device.trim();
        String employeeidWithoutSpaces = employeeid.trim();
        String itstaffidWithoutSpaces = itstaffid.trim();
        try {
            int dateCheck = Integer.parseInt(dateWithoutSpaces);
            if (dateCheck <= 0 || date.length() < 6) {
                System.out.println("*** WRONG DATE ***");
            } else {
                List<Devices> devicesList = devicesService.getDevicesByName(deviceWithoutSpaces);
                List<Employee> employeeList = employeeService.findEmployeeByFio(employeeidWithoutSpaces);
                List<ITStaff> itStaffList = itStaffService.getITStaffByName(itstaffidWithoutSpaces);
                Events events = new Events(date,
                        devicesList.get(0),
                        employeeList.get(0),
                        itStaffList.get(0));
                eventsService.addNewEvent(events);
                List<Events> eventsList = eventsService.findAllEvents();
                model.addAttribute("eventsList", eventsList);
            }
            return this.getEvents(model);
        } catch (Exception e) {
            System.out.println("*** WRONG DATE FORMAT ***");
           return this.getEvents(model);
        }
    }

    @PostMapping("/deleteevent")
    public String deleteEvent(@RequestParam String id,
                              Model model) {
        try {
            int idCheck = Integer.parseInt(id);
            if (idCheck <= 0) {
                System.out.println("*** SUB ZERO ID***");
            } else {
                eventsService.deleteEventsById(idCheck);
                List<Events> eventsList = eventsService.findAllEvents();
                model.addAttribute("eventsList", eventsList);
            }
            return this.getEvents(model);
        } catch (Exception e) {
            System.out.println("*** WRONG ID FORMAT***");
            return this.getEvents(model);
        }

    }

    @PostMapping("/updateevent")
    public String updateEvent(@RequestParam String id,
                              @RequestParam String date,
                              @RequestParam String device,
                              @RequestParam String employeeid,
                              @RequestParam String itstaffid,
                              Model model) {

        String idWithoutSpaces = id.trim();
        String dateWithoutSpaces = date.trim();
        String deviceWithoutSpaces = device.trim();
        String employeeIdWithoutSpaces = employeeid.trim();
        String itstaffIdWithoutSpaces = itstaffid.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB ZERO ID***");
                return this.getEvents(model);
            } else if (!dateWithoutSpaces.equals("") &&
                    !deviceWithoutSpaces.equals("") &&
                    !employeeIdWithoutSpaces.equals("") &&
                    !itstaffIdWithoutSpaces.equals("")) {
                List<Devices> devicesList = devicesService.getDevicesByName(deviceWithoutSpaces);
                List<Employee> employeeList = employeeService.findEmployeeByFio(employeeIdWithoutSpaces);
                List<ITStaff> itStaffList = itStaffService.getITStaffByName(itstaffIdWithoutSpaces);
                Events events = new Events(idCheck,
                        dateWithoutSpaces,
                        devicesList.get(0),
                        employeeList.get(0),
                        itStaffList.get(0));
                eventsService.updateEvent(events);
                List<Events> eventsList = eventsService.findAllEvents();
                model.addAttribute("eventsList", eventsList);
            }
            return this.getEvents(model);

        } catch (Exception e) {
            System.out.println("*** WRONG ID FORMAT***");
            this.getEvents(model);
            return this.getEvents(model);
        }
    }

    @PostMapping("/findeventbyid")
    public String findEventsById(@RequestParam String id,
                                 Model model) {

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB ZERO ID***");
                this.getEvents(model);
            } else {
                List<Events> eventsList;
                eventsList = eventsService.getEventById(idCheck);
                model.addAttribute("eventsList", eventsList);
            }
            return "events";
        } catch (Exception e) {
            System.out.println("*** WRONG ID FORMAT***");
            return this.getEvents(model);
        }

    }
}
