package accountingApp.controller;

import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.RoomService;

import accountingApp.service.WorkAreaService;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class RoomControllerTest {

    @InjectMocks
    private RoomController roomController;
    @Mock
    private RoomService roomService;
    @Mock
    private WorkAreaService workAreaService;
    @Mock
    private Model model;
    @Mock
    private Checker checker;

    private final List<Room> roomList;
    private String description;

    {
        Room r1 = new Room();
        Room r2 = new Room();
        Room r3 = new Room();
        Room r4 = new Room();
        description = "description";

        roomList = new ArrayList<>();
        roomList.add(r1);
        roomList.add(r2);
        roomList.add(r3);
        roomList.add(r4);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRoomShouldReturnRoomList() {

        Mockito.when(roomService.findAllRoom()).thenReturn(roomList);

        String viewName = roomController.getRoom(model);

        Assertions.assertEquals("room", viewName);

        verify(model).addAttribute("roomList", roomList);

        verify(roomService).findAllRoom();
    }

    @Test
    void addRoomValid() {

        String number = "3";
        String workAreaId = "1";

        WorkArea workArea = new WorkArea(workAreaId, description);

        Mockito.when(roomService.findAllRoom()).thenReturn(roomList);

        String viewName = roomController.addRoom(number, workArea
                , description, model);

        Assertions.assertEquals("room", viewName);

        verify(model, times(1)).addAttribute("roomList", roomList);

    }

    @Test
    void addRoomFail() {

        String number = "-15";
        String workAreaId = "-1";
        String numberWithoutSpaces = number.trim();
        description = " ";
        WorkArea workArea = new WorkArea(workAreaId, description);

        String viewName = roomController.addRoom(numberWithoutSpaces,workArea
                , description, model);

        Assertions.assertEquals("room", viewName);

        verify(model, never()).addAttribute("roomList", roomList);

        verify(roomService, never()).addNewRoom(any());
    }

    @Test
    void addRoomFailWithException() {

        String number = "15";
        String workAreaId = "1";
        String numberWithoutSpaces = number.trim();
        WorkArea workArea = new WorkArea(workAreaId, description);

        String viewName = roomController.addRoom(numberWithoutSpaces, workArea
                , description, model);

        Assertions.assertEquals("room", viewName);

        doThrow(new RuntimeException()).when(roomService).addNewRoom(any());

        verify(model, never()).addAttribute("roomList", roomList);

        verify(roomService, never()).addNewRoom(new Room());
    }

    @Test
    void deleteRoomValid() {

        String number = "1";
        String numberWithoutSpaces = number.trim();
        int numberCheck = Integer.parseInt(numberWithoutSpaces);

        String viewName = roomController.deleteRoom(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, never()).deleteRoomById(numberCheck);
    }

    @Test
    void deleteRoomFail() {

        String number = "-45";
        String numberWithoutSpaces = number.trim();
        int numberCheck = Integer.parseInt(numberWithoutSpaces);

        String viewName = roomController.deleteRoom(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, never()).deleteRoomById(numberCheck);
    }

    @Test
    void deleteRoomFailWithException() {

        String number = "45";
        String numberWithoutSpaces = number.trim();
        int numberCheck = Integer.parseInt(numberWithoutSpaces);

        doThrow(new RuntimeException()).when(roomService).deleteRoomById(numberCheck);

        verify(roomService, never()).deleteRoomById(numberCheck);
    }

    @Test
    void updateRoomValid() {

        String id = "1";
        String number = "45";
        String workAreaId = "1";
        String numberWithoutSpaces = number.trim();
        String idWithoutSpaces = id.trim();

        String viewName = roomController
                .updateRoom(idWithoutSpaces, numberWithoutSpaces, new WorkArea(workAreaId, description)
                        , description, model);

        Assertions.assertEquals("room", viewName);
    }

    @Test
    void updateRoomFail() {

        String id = "-1";
        String number = "-45";
        String workAreaId = "-1";
        String numberWithoutSpaces = number.trim();
        String idWithoutSpaces = id.trim();
        description = " ";

        String viewName = roomController
                .updateRoom(idWithoutSpaces, numberWithoutSpaces,new WorkArea(workAreaId, description)
                        , description, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, never()).updateRoom(new Room());
    }

    @Test
    void updateRoomFailWithException() {

        String id = "1";
        String number = "45";
        String idWithoutSpaces = id.trim();


        doThrow(new RuntimeException()).when(roomService)
                .updateRoom(any());

        verify(roomService, never()).updateRoom(new Room());
    }

    @Test
    void findRoomValid() {

        String number = "45";
        String numberWithoutSpaces = number.trim();
        int numberCheck = Integer.parseInt(numberWithoutSpaces);

        String viewName = roomController.findRoomById(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, times(1)).getRoomById(numberCheck);
    }

    @Test
    void findRoomFail() {

        String number = "-45";
        String numberWithoutSpaces = number.trim();
        int numberCheck = Integer.parseInt(numberWithoutSpaces);

        String viewName = roomController.findRoomById(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, never()).getRoomById(numberCheck);
    }

    @Test
    void findRoomFailWithException() {

        String number = "45";
        String numberWithoutSpaces = number.trim();
        int numberCheck = Integer.parseInt(numberWithoutSpaces);

        doThrow(new RuntimeException()).when(roomService).getRoomById(numberCheck);

        verify(roomService, never()).getRoomById(numberCheck);
    }
}