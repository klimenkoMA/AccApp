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
            return "events";
        } catch (Exception e) {
            System.out.println("*** WRONG DATE FORMAT ***");
            return "events";
        }
    }

    @PostMapping("/deleteevent")
    public String deleteEvent(@RequestParam int id,
                              Model model) {
        eventsService.deleteEventsById(id);
        List<Events> eventsList = eventsService.findAllEvents();
        model.addAttribute("eventsList", eventsList);
        return "events";
    }

    @PostMapping("/updateevent")
    public String updateEvent(@RequestParam int id,
                              @RequestParam String date,
                              @RequestParam String device,
                              @RequestParam String employeeid,
                              @RequestParam String itstaffid,
                              Model model) {
        List<Devices> devicesList = devicesService.getDevicesByName(device);
        List<Employee> employeeList = employeeService.findEmployeeByFio(employeeid);
        List<ITStaff> itStaffList = itStaffService.getITStaffByName(itstaffid);
        Events events = new Events(id,
                date,
                devicesList.get(0),
                employeeList.get(0),
                itStaffList.get(0));
        eventsService.updateEvent(events);
        List<Events> eventsList = eventsService.findAllEvents();
        model.addAttribute("eventsList", eventsList);
        return "events";
    }

    @PostMapping("/findeventbyid")
    public String findEventsById(@RequestParam int id,
                                 Model model) {
        List<Events> eventsList;
        if (id > 0) {
            eventsList = eventsService.getEventById(id);
        } else {
            eventsList = eventsService.findAllEvents();
        }
        model.addAttribute("eventsList", eventsList);
        return "events";
    }
}
