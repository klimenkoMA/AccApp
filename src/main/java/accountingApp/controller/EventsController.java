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
        List<Devices> devicesList = devicesService.getDevicesByName(device);
        List<Employee> employeeList = employeeService.findEmployeeByFio(employeeid);
        List<ITStaff> itStaffList = itStaffService.getITStaffByName(itstaffid);
        Events events = new Events(date,
                devicesList.get(0),
                employeeList.get(0),
                itStaffList.get(0));
        eventsService.addNewEvent(events);
        List<Events> eventsList = eventsService.findAllEvents();
        model.addAttribute("eventsList", eventsList);
        return "events";
    }

//	@PostMapping("/deleterecreantsaegers")
//	public String deleteAegerCaptions(@RequestParam int id, Model model) {
//		eventsService.deleteRecreantsAegersById(id);
//		List<Events> eventsList = eventsService.findAllRecreantsAegers();
//		model.addAttribute("eventsList", eventsList);
//		return "events";
//	}

//	@PostMapping("/updaterecreantsaegers")
//	public String updateAegerCaptions(@RequestParam int id,
//	                                  @RequestParam int date,
//	                                  @RequestParam int device,
//	                                  Model model) {
//		List<WorkArea> workAreaList = workAreaService.getAegerCaptionsById(device);
//		Events events = new Events(id,workAreaList.get(0));
//		eventsService.addNewRecreantsAegers(events);
//		List<Events> eventsList = eventsService.findAllRecreantsAegers();
//		model.addAttribute("eventsList", eventsList);
//		return "events";
//	}

//	@PostMapping("findrecreantsaegersbyid")
//	public String findRecreantsAegersById(@RequestParam int findbyid, Model model) {
//
//		if(findbyid > 0) {
//			List<Events> eventsList = eventsService.getRecreantsAegersById(findbyid);
//			model.addAttribute("eventsList", eventsList);
//		} else {
//			List<Events> eventsList = eventsService.findAllRecreantsAegers();
//			model.addAttribute("eventsList", eventsList);
//		}
//		return "events";
//	}
}
