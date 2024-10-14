package accountingApp.controller;


import accountingApp.entity.Devices;
import accountingApp.entity.Employee;
import accountingApp.entity.Events;
import accountingApp.entity.ITStaff;
import accountingApp.service.EventsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class EventsControllerTest {

    @InjectMocks
    private EventsController eventsController;

    @Mock
    private Model model;

    @Mock
    private EventsService eventsService;

    @Mock
    private EmployeeController employeeController;

    @Mock
    private ITStaffController itStaffController;

    @Mock
    private DevicesController devicesController;

    private final List<Events> eventsList;

    {
        Employee emp1 = new Employee(1, "08061974", "Сатурнов", "ПТЦ", "105");
        Employee emp2 = new Employee(2, "17111984", "Букина", "ФГУ", "205");
        Employee emp3 = new Employee(3, "22011994", "Загаев", "МГУ", "1");

        ITStaff it1 = new ITStaff(1, "Клименко");
        ITStaff it2 = new ITStaff(2, "Плотникова");

        Devices dev1 = new Devices(1, "Kyocera");
        Devices dev2 = new Devices(2, "HP");
        Devices dev3 = new Devices(3, "Acer");

        Events ev1 = new Events(1, "23012024", dev1, emp1, it1);
        Events ev2 = new Events(2, "15012024", dev2, emp2, it1);
        Events ev3 = new Events(3, "31112023", dev3, emp3, it2);

        eventsList = new ArrayList<>();
        eventsList.add(ev1);
        eventsList.add(ev2);
        eventsList.add(ev3);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEventsShouldReturnEventsList() {

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.getEvents(model);

        Assertions.assertEquals("events", viewName);

        verify(model).addAttribute("eventsList", eventsList);

        verify(eventsService).findAllEvents();

    }

    @Test
    void addEventsSuccess() {

        String dateWithoutSpaces = "5201995";
        String deviceWithoutSpaces = "printer";
        String employeeidWithoutSpaces = "Дапниев";
        String itstaffidWithoutSpaces = "Элинская";

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.addNewEvent(dateWithoutSpaces, deviceWithoutSpaces,
                employeeidWithoutSpaces, itstaffidWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);
    }

    @Test
    void addEventsFail() {

        String dateWithoutSpaces = " ";
        String deviceWithoutSpaces = " ";
        String employeeidWithoutSpaces = " ";
        String itstaffidWithoutSpaces = " ";

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.addNewEvent(dateWithoutSpaces, deviceWithoutSpaces,
                employeeidWithoutSpaces, itstaffidWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);

        verify(eventsService, never()).addNewEvent(any(Events.class));
    }

    @Test
    void deleteEventsSuccess() {

        String dateWithoutSpaces = "1";
        int idCheck = Integer.parseInt(dateWithoutSpaces);

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.deleteEvent(dateWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);

        verify(eventsService).deleteEventsById(idCheck);
    }

    @Test
    void deleteEventsFail() {

        String dateWithoutSpaces = "0";
        int idCheck = Integer.parseInt(dateWithoutSpaces);

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.deleteEvent(dateWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);

        verify(eventsService, never()).deleteEventsById(idCheck);
    }

    @Test
    void deleteEventsFailWithException() {

        String dateWithoutSpaces = "1";
        int idCheck = Integer.parseInt(dateWithoutSpaces);

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        doThrow(new RuntimeException()).when(eventsService).deleteEventsById(idCheck);

        verify(eventsService, never()).deleteEventsById(idCheck);
    }

    @Test
    void updateEventsSuccess() {

        String eventsId = "1";
        String dateWithoutSpaces = "5201995";
        String deviceWithoutSpaces = "printer";
        String employeeidWithoutSpaces = "Дапниев";
        String itstaffidWithoutSpaces = "Элинская";

        int idCheck = Integer.parseInt(eventsId);
        int dateCheck = Integer.parseInt(dateWithoutSpaces);

        Events event = new Events(idCheck, dateWithoutSpaces, new Devices(),
                new Employee(), new ITStaff());

        eventsService.addNewEvent(event);

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.updateEvent(eventsId, dateWithoutSpaces, deviceWithoutSpaces,
                employeeidWithoutSpaces, itstaffidWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);

//        verify(eventsService).updateEvent(event);
    }
}
