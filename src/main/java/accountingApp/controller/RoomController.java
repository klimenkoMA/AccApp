package accountingApp.controller;

import accountingApp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/room")
    public String getRoom(Model model) {
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "room";
    }

    @PostMapping("/addroom")
    public String addRoom(@RequestParam String number,
                          Model model) {
        String numberWithoutSpaces = number.trim();
        try {
            int numberCheck = Integer.parseInt(numberWithoutSpaces);
            if (numberCheck <= 0) {
                System.out.println("*** SUB ZERO NUMBER***");
                return "room";
            } else {
                Room room = new Room(numberWithoutSpaces);
                roomService.addNewRoom(room);
                List<Room> roomList = roomService.findAllRoom();
                model.addAttribute("roomList", roomList);
            }
            return "room";
        } catch (Exception e) {
            System.out.println("*** WRONG NUMBER TYPE***");
            return "room";
        }


    }

    @PostMapping("/deleteroom")
    public String deleteRoom(@RequestParam int id, Model model) {
        roomService.deleteRoomById(id);
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "room";
    }

    @PostMapping("/updateroom")
    public String updateRoom(@RequestParam int id,
                             @RequestParam String number,
                             Model model) {
        Room room = new Room(id, number);
        roomService.updateRoom(room);
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "room";
    }

    @PostMapping("/findroomyid")
    public String findRoomById(@RequestParam int id,
                               Model model) {
        if (id > 0) {
            List<Room> roomList = roomService.getRoomById(id);
            model.addAttribute("roomList", roomList);
        } else {
            List<Room> roomList = roomService.findAllRoom();
            model.addAttribute("roomList", roomList);
        }
        return "room";
    }
}
