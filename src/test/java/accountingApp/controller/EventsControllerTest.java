package accountingApp.controller;


import accountingApp.entity.*;
import accountingApp.service.*;

import accountingApp.usefulmethods.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
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
    private EmployeeService employeeService;
    @Mock
    private ITStaffService itStaffService;
    @Mock
    private DevicesService devicesService;
    @Mock
    private WorkAreaService workAreaService;
    @Mock
    private Checker checker;

    private final List<Events> eventsList;
    private String description;
    private long inventory;
    private Room room;
    private Employee employee;
    private ITStaff itstaff;
    private DeviceCategory category;
    private Profession profession;
    private String serial;

    {
        category = DeviceCategory.Компьютер;
        profession = Profession.Преподаватель;
        description = "Description";
        inventory = 111111L;
        serial = "SdSd233445t";
        room = new Room();
        employee = new Employee();
        itstaff = new ITStaff();
        Employee emp1 = new Employee(1
                , "Сатурнов"
                , "08061974"
                , profession
                , new WorkArea("ПТЦ"
                , description)
                , new Room("111", new WorkArea()
                , description));
        Employee emp2 = new Employee(2
                , "Букина"
                , "17111984"
                , profession
                , new WorkArea("ФГУ", description)
                , new Room("205", new WorkArea()
                , description));
        Employee emp3 = new Employee(3
                , "Загаев"
                , "22011994"
                , profession
                , new WorkArea("МГУ", description), new Room("15", new WorkArea(), description));

        ITStaff it1 = new ITStaff(1
                , "Клименко"
                , profession);
        ITStaff it2 = new ITStaff(2
                , "Плотникова"
                , profession);

        Devices dev1 = new Devices(1
                , category
                , "Kyocera"
                , description
                , inventory
                , serial
                , room
                , emp1
                , it1);
        Devices dev2 = new Devices(2
                , category
                , "HP"
                , description
                , inventory
                , serial
                , room
                , emp2
                , it1);
        Devices dev3 = new Devices(3
                , category
                , "Acer"
                , description
                , inventory
                , serial
                , room
                , emp3
                , it2);

        WorkArea wa1 = new WorkArea("BGU", description);
        WorkArea wa2 = new WorkArea("TTN", description);
        WorkArea wa3 = new WorkArea("KubSTU", description);

        String r1 = "На курс лекций";
        String r2 = "Возврат";
        String r3 = "В командировку";

        Events ev1 = new Events(1, "23012024", dev1, emp1, it1, wa1
                , r1);
        Events ev2 = new Events(2, "15012024", dev2, emp2, it1, wa2
                , r2);
        Events ev3 = new Events(3, "31112023", dev3, emp3, it2, wa3
                , r3);

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
        String workareaWithoutSpaces = "GTU";
        String commentWithoutSpaces = "Взял на курс лекций";


        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.addNewEvent(dateWithoutSpaces
                , new Devices(category
                        , deviceWithoutSpaces
                        , description
                        , inventory
                        , serial
                        , room
                        , employee
                        , itstaff)
                , new Employee(1
                        , "Сатурнов"
                        , employeeidWithoutSpaces
                        , profession
                        , new WorkArea(workareaWithoutSpaces, description)
                        , new Room("111"
                        , new WorkArea("BGU", description)
                        , description))
                , new ITStaff(1, itstaffidWithoutSpaces, profession)
                , new WorkArea("BGU", description)
                , commentWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);
    }

    @Test
    void addEventsFail() {

        String dateWithoutSpaces = " ";
        String deviceWithoutSpaces = " ";
        String employeeidWithoutSpaces = " ";
        String itstaffidWithoutSpaces = " ";
        String workareaWithoutSpaces = " ";
        String commentWithoutSpaces = " ";
        description = " ";
        inventory = -1;

        Events event = new Events(dateWithoutSpaces, new Devices(category
                , deviceWithoutSpaces
                , description
                , inventory
                , serial
                , room
                , employee
                , itstaff),
                new Employee(" "
                        , " "
                        , profession
                        , new WorkArea(" ", " ")
                        , new Room(" ", new WorkArea(), description)), new ITStaff(" "
                , profession),
                new WorkArea(workareaWithoutSpaces, description), commentWithoutSpaces);

        Mockito.when(eventsService.findAllEvents()).thenReturn(eventsList);

        String viewName = eventsController.addNewEvent(dateWithoutSpaces
                , new Devices(category
                        , deviceWithoutSpaces
                        , description
                        , inventory
                        , serial
                        , room
                        , employee
                        , itstaff)
                , new Employee(1
                        , "Сатурнов"
                        , employeeidWithoutSpaces
                        , profession
                        , new WorkArea(workareaWithoutSpaces, description),
                        new Room("111", new WorkArea("BGU", description)
                                , description))
                , new ITStaff(1
                        , itstaffidWithoutSpaces
                        , profession)
                , new WorkArea("BGU", description)
                , commentWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);

        verify(eventsService, never()).addNewEvent(event);
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
        String workareaWithoutSpaces = "GTU";
        String commentWithoutSpaces = "Взял на курс лекций";

        int idCheck = Integer.parseInt(eventsId);

        Events event = new Events(idCheck, dateWithoutSpaces, new Devices(category
                , deviceWithoutSpaces
                , description
                , inventory
                , serial
                , room
                , employee
                , itstaff),
                new Employee("1"
                        , "2"
                        , profession
                        , new WorkArea("3", description)
                        , new Room("1", new WorkArea(), description)), new ITStaff("trrr"
                , profession),
                new WorkArea(workareaWithoutSpaces, description), commentWithoutSpaces);

        List<Events> events = Collections.singletonList(event);


        Mockito.when(eventsService.findAllEvents()).thenReturn(events);

        String viewName = eventsController.updateEvent(eventsId, dateWithoutSpaces
                , new Devices(category
                        , deviceWithoutSpaces
                        , description
                        , inventory
                        , serial
                        , room
                        , employee
                        , itstaff)
                , new Employee(1
                        , "Сатурнов"
                        , employeeidWithoutSpaces
                        , profession
                        , new WorkArea(workareaWithoutSpaces, description)
                        , new Room("111", new WorkArea("BGU", description)
                        , description))
                , new ITStaff(1, itstaffidWithoutSpaces, profession)
                , new WorkArea("BGU", description)
                , commentWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);

    }

    @Test
    void updateEventsFail() {

        String eventsId = "1";
        String dateWithoutSpaces = " ";
        String deviceWithoutSpaces = " ";
        String employeeidWithoutSpaces = " ";
        String itstaffidWithoutSpaces = " ";
        String workareaWithoutSpaces = " ";
        String commentWithoutSpaces = " ";
        description = " ";
        inventory = -1;
        int idCheck = Integer.parseInt(eventsId);

        Events event = new Events(idCheck, dateWithoutSpaces, new Devices(category
                , deviceWithoutSpaces
                , description
                , inventory
                , serial
                , room
                , employee
                , itstaff),
                new Employee(" "
                        , " "
                        , profession
                        , new WorkArea(" ", " ")
                        , new Room(" ", new WorkArea(), description)), new ITStaff(" "
        , profession),
                new WorkArea(workareaWithoutSpaces, description), commentWithoutSpaces);

        List<Events> events = Collections.singletonList(event);

        Mockito.when(eventsService.findAllEvents()).thenReturn(events);

        String viewName = eventsController.updateEvent(eventsId
                , dateWithoutSpaces
                , new Devices(category
                        , deviceWithoutSpaces
                        , description
                        , inventory
                        , serial
                        , room
                        , employee
                        , itstaff)
                , new Employee(1
                        , "Сатурнов"
                        , employeeidWithoutSpaces
                        , profession
                        , new WorkArea(workareaWithoutSpaces
                        , description)
                        , new Room("111"
                        , new WorkArea("BGU"
                        , description)
                        , description))
                , new ITStaff(1, itstaffidWithoutSpaces, profession)
                , new WorkArea("BGU", description)
                , commentWithoutSpaces, model);

        Assertions.assertEquals("events", viewName);

        Mockito.verify(this.eventsService, never()).updateEvent(event);

    }

    @Test
    void updateEventsFailWithException() {

        String eventsId = "1";
        String dateWithoutSpaces = "12334455";
        String deviceWithoutSpaces = "ertert ";
        String itstaffidWithoutSpaces = "yuuyu ";
        String workareaWithoutSpaces = "GTU";
        String commentWithoutSpaces = "Взял на курс лекций";

        int idCheck = Integer.parseInt(eventsId);

        Events event = new Events(idCheck, dateWithoutSpaces, new Devices(category
                , deviceWithoutSpaces
                , description
                , inventory
                , serial
                , room
                , employee
                , itstaff),
                new Employee(idCheck
                        , "dfd"
                        , "fdf "
                        , profession
                        , new WorkArea("12 ", description)
                        , new Room("32"
                        , new WorkArea(), description)),
                new ITStaff(itstaffidWithoutSpaces, profession)
                , new WorkArea(workareaWithoutSpaces, description)
                , commentWithoutSpaces);

        doThrow(new RuntimeException()).when(eventsService).updateEvent(event);

        Mockito.verify(this.eventsService, never()).updateEvent(any(Events.class));
    }

    @Test
    void findEventsSuccess() {

        String eventsId = "1";

        int idCheck = Integer.parseInt(eventsId);

        List<Events> events = Collections.singletonList(any(Events.class));

        Mockito.when(eventsService.getEventById(idCheck)).thenReturn(events);

        String viewName = eventsController.findEventsById(eventsId, model);

        Assertions.assertEquals("events", viewName);

        Mockito.verify(this.eventsService).getEventById(idCheck);

    }

    @Test
    void findEventsFail() {

        String eventsId = "-1";

        int idCheck = Integer.parseInt(eventsId);

        List<Events> events = Collections.singletonList(any(Events.class));

        Mockito.when(eventsService.getEventById(idCheck)).thenReturn(events);

        String viewName = eventsController.findEventsById(eventsId, model);

        Assertions.assertEquals("events", viewName);

        Mockito.verify(this.eventsService, never()).getEventById(idCheck);

        verify(model, never()).addAttribute("eventsList", events);

    }

    @Test
    void findEventsFailWithException() {

        String eventsId = "1";

        int idCheck = Integer.parseInt(eventsId);

        doThrow(new RuntimeException()).when(eventsService).getEventById(idCheck);

        Mockito.verify(this.eventsService, never()).getEventById(idCheck);

        verify(model, never()).addAttribute("eventsList", eventsList);

    }

}
