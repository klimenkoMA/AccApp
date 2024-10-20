package accountingApp.controller;

import accountingApp.entity.Room;
import accountingApp.service.RoomService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class RoomControllerTest {

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomService;

    @Mock
    private Model model;

    private final List<Room> roomList;

    {
        Room r1 = new Room();
        Room r2 = new Room();
        Room r3 = new Room();
        Room r4 = new Room();

        roomList = Arrays.asList(r1, r2, r3, r4);
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

        String number = "45";
        String numberWithoutSpaces = number.trim();

        Mockito.when(roomService.findAllRoom()).thenReturn(roomList);

        String viewName = roomController.addRoom(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(model, times(2)).addAttribute("roomList", roomList);

        verify(roomService, times(1)).addNewRoom(any());
    }

    @Test
    void addRoomFail() {

        String number = "-15";
        String numberWithoutSpaces = number.trim();

        String viewName = roomController.addRoom(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(model, never()).addAttribute("roomList", roomList);

        verify(roomService, never()).addNewRoom(any());
    }

    @Test
    void addRoomFailWithException() {

        String number = "15";
        String numberWithoutSpaces = number.trim();

        String viewName = roomController.addRoom(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        doThrow(new RuntimeException()).when(roomService).addNewRoom(any());

        verify(model, never()).addAttribute("roomList", roomList);

        verify(roomService, never()).addNewRoom(new Room());
    }

    @Test
    void deleteRoomValid() {

        String number = "45";
        String numberWithoutSpaces = number.trim();
        int numberCheck = Integer.parseInt(numberWithoutSpaces);

        String viewName = roomController.deleteRoom(numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, times(1)).deleteRoomById(numberCheck);
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
        String numberWithoutSpaces = number.trim();
        String idWithoutSpaces = id.trim();

        String viewName = roomController.updateRoom(idWithoutSpaces, numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, times(1)).updateRoom(any());
    }

    @Test
    void updateRoomFail() {

        String id = "-1";
        String number = "45";
        String numberWithoutSpaces = number.trim();
        String idWithoutSpaces = id.trim();

        String viewName = roomController.updateRoom(idWithoutSpaces, numberWithoutSpaces, model);

        Assertions.assertEquals("room", viewName);

        verify(roomService, never()).updateRoom(new Room());
    }

    @Test
    void updateRoomFailWithException() {

        String id = "1";
        String number = "45";
        String numberWithoutSpaces = number.trim();
        String idWithoutSpaces = id.trim();
        int idCheck = Integer.parseInt(idWithoutSpaces);

        doThrow(new RuntimeException()).when(roomService).updateRoom(new Room(idCheck, numberWithoutSpaces));

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