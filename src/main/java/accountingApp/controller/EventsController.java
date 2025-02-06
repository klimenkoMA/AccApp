package accountingApp.controller;

import accountingApp.entity.*;
import accountingApp.service.*;
import accountingApp.usefulmethods.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class EventsController {

    final Logger logger = LoggerFactory.getLogger(EventsController.class);

    @Autowired
    private EventsService eventsService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ITStaffService itStaffService;
    @Autowired
    private DevicesService devicesService;
    @Autowired
    private WorkAreaService workAreaService;
    @Autowired
    private Checker checker;

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
                              @RequestParam(required = false) Devices device,
                              @RequestParam(required = false) Employee employee,
                              @RequestParam(required = false) ITStaff itstaff,
                              @RequestParam(required = false) WorkArea workarea,
                              @RequestParam String comment,
                              Model model) {

        if (checker.checkAttribute(date)
                || device == null
                || employee == null
                || itstaff == null
                || workarea == null
                || checker.checkAttribute(comment)
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
            if (!checker.checkAttribute(dateWithoutSpaces)
                    && !checker.checkAttribute(deviceWithoutSpaces)
                    && !checker.checkAttribute(employeeIdWithoutSpaces)
                    && !checker.checkAttribute(workareaWithoutSpaces)
                    && !checker.checkAttribute(itstaffIdWithoutSpaces)
                    && !checker.checkAttribute(commentWithoutSpaces)
            ) {

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
        if (checker.checkAttribute(id)
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
                              @RequestParam(required = false) Devices device,
                              @RequestParam(required = false) Employee employee,
                              @RequestParam(required = false) ITStaff itstaff,
                              @RequestParam(required = false) WorkArea workarea,
                              @RequestParam String comment,
                              Model model) {

        if (checker.checkAttribute(id)
                || checker.checkAttribute(date)
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
            } else if (!checker.checkAttribute(dateWithoutSpaces)
                    && !checker.checkAttribute(deviceWithoutSpaces)
                    && !checker.checkAttribute(employeeIdWithoutSpaces)
                    && !checker.checkAttribute(workareaWithoutSpaces)
                    && !checker.checkAttribute(itstaffIdWithoutSpaces)
                    && !checker.checkAttribute(commentWithoutSpaces)
            ) {

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
        if (checker.checkAttribute(id)
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

    @PostMapping("/findeventbyattrs")
    public String findEventsByAttrs(@RequestParam String attrs,
                                    Model model) {
        if (checker.checkAttribute(attrs)
        ) {
            logger.warn("*** EventsController.findEventsByAttrs():  Attribute ID has a null value! ***");
            return getEvents(model);
        }

        String attrsWithoutSpaces = attrs.trim().toLowerCase(Locale.ROOT);
        try {
            List<Events> events = eventsService.findAllEvents();
            List<Events> eventsList = new ArrayList<>();

            for (Events ev : events
            ) {
                if ((ev.getId() + "").contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                } else if (ev.getComment().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                } else if (ev.getDate().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                } else if (ev.getWorkarea().getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                } else if (ev.getDevice().getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                } else if (ev.getEmployeeid().getFio().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                } else if (ev.getItstaffid().getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                } else if (ev.getDevice().getCategory().getCategory().toLowerCase()
                        .contains(attrsWithoutSpaces)) {
                    eventsList.add(ev);
                }
            }

            if (eventsList.isEmpty()) {
                logger.debug("*** EventsController.findEventsByAttrs():  DATA NOT FOUND IN DB***");
                return getEvents(model);
            }

            model.addAttribute("eventsList", eventsList);
            return "events";
        } catch (Exception e) {
            logger.error("*** EventsController.findEventsByAttrs():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getEvents(model);
        }
    }
}
