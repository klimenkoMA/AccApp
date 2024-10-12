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

import static org.mockito.Mockito.verify;

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
    void addRoom() {
    }

    @Test
    void deleteRoom() {
    }

    @Test
    void updateRoom() {
    }

    @Test
    void findRoomById() {
    }
}