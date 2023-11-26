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
    public String deleteRoom(@RequestParam String id,
                             Model model) {
        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB ZERO ID***");
            } else {
                roomService.deleteRoomById(idCheck);
                List<Room> roomList = roomService.findAllRoom();
                model.addAttribute("roomList", roomList);
            }
            return "room";
        } catch (Exception e) {
            System.out.println("*** WRONG ID TYPE***");
            return "room";
        }

    }

    @PostMapping("/updateroom")
    public String updateRoom(@RequestParam String id,
                             @RequestParam String number,
                             Model model) {
        String idWithoutSpaces = id.trim();
        String numberWithoutSpaces = number.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            int numberCheck = Integer.parseInt(numberWithoutSpaces);
            if (idCheck <= 0 || numberCheck <= 0) {
                System.out.println("*** SUB ZERO ID OR NUMBER***");
            } else {
                Room room = new Room(idCheck, number);
                roomService.updateRoom(room);
                List<Room> roomList = roomService.findAllRoom();
                model.addAttribute("roomList", roomList);
            }
            return "room";
        } catch (Exception e) {
            System.out.println("*** WRONG ID OR NUMBER TYPE***");
            return "room";
        }
    }

    @PostMapping("/findroomyid")
    public String findRoomById(@RequestParam String id,
                               Model model) {
        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB ZERO ID OR NUMBER***");
            } else {
                List<Room> roomList = roomService.getRoomById(idCheck);
                model.addAttribute("roomList", roomList);
            }
            return "room";
        } catch (Exception e) {
            System.out.println("*** WRONG ID OR NUMBER TYPE***");
            return "room";
        }
    }
}
