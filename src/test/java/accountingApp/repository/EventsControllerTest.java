package accountingApp.repository;


import accountingApp.controller.EventsController;
import accountingApp.entity.Devices;
import accountingApp.entity.Employee;
import accountingApp.entity.Events;
import accountingApp.entity.ITStaff;
import accountingApp.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(EventsController.class)
public class EventsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    EventsService service;

    private List<Events> getEvents() {

        Employee emp1 = new Employee(1, "08061974", "Сатурнов", "УПЦ", "105");
        Employee emp2 = new Employee(2, "17111984", "Букина", "УПЦ", "205");
        Employee emp3 = new Employee(3, "22011994", "Загаев", "Полигон", "1");

        ITStaff it1 = new ITStaff(1, "Клименко");
        ITStaff it2 = new ITStaff(2, "Игумнова");

        Devices dev1 = new Devices(1, "Kyocera");
        Devices dev2 = new Devices(2, "HP");
        Devices dev3 = new Devices(3, "Acer");

        Events ev1 = new Events(1, "23012024", dev1, emp1, it1);
        Events ev2 = new Events(2, "15012024", dev2, emp2, it1);
        Events ev3 = new Events(3, "31112023", dev3, emp3, it2);

        List<Events> events = new ArrayList<>();
        events.add(ev1);
        events.add(ev2);
        events.add(ev3);

        return events;

    }


}
