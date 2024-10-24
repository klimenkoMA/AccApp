package accountingApp.controller;

import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.WorkAreaService;
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
    @Autowired
    WorkAreaService workAreaService;

    @GetMapping("/room")
    public String getRoom(Model model) {
        List<Room> roomList = roomService.findAllRoom();
        model.addAttribute("roomList", roomList);
        return "room";
    }

    @PostMapping("/addroom")
    public String addRoom(@RequestParam String number,
                          @RequestParam String workarea,
                          Model model) {
        String numberWithoutSpaces = number.trim();
        String workAreaIdWithoutSpaces = workarea.trim();
        try {
            int numberCheck = Integer.parseInt(numberWithoutSpaces);
            int workAreaIdCheck = Integer.parseInt(workAreaIdWithoutSpaces);
            if (numberCheck <= 0 || workAreaIdCheck <= 0) {
                System.out.println("*** SUB ZERO NUMBER OR WORKAREA ID***");
                return this.getRoom(model);
            } else {
                List<WorkArea> workAreaList = workAreaService.getWorkAreaById(workAreaIdCheck);
                Room room = new Room(numberWithoutSpaces, workAreaList.get(0));
                roomService.addNewRoom(room);
                List<Room> roomList = roomService.findAllRoom();
                model.addAttribute("roomList", roomList);
            }
            return this.getRoom(model);
        } catch (Exception e) {
            System.out.println("*** WRONG NUMBER TYPE***");
            return this.getRoom(model);
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
            return this.getRoom(model);
        } catch (Exception e) {
            System.out.println("*** WRONG ID TYPE***");
            return this.getRoom(model);
        }

    }

    @PostMapping("/updateroom")
    public String updateRoom(@RequestParam String id,
                             @RequestParam String number,
                             @RequestParam String workarea,
                             Model model) {
        String idWithoutSpaces = id.trim();
        String numberWithoutSpaces = number.trim();
        String workAreaIdWithoutSpaces = workarea.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            int numberCheck = Integer.parseInt(numberWithoutSpaces);
            int workAreaIdCheck = Integer.parseInt(workAreaIdWithoutSpaces);
            if (idCheck <= 0 || numberCheck <= 0 || workAreaIdCheck <= 0) {
                System.out.println("*** SUB ZERO ID OR NUMBER***");
            } else {
                List<WorkArea> workAreaList = workAreaService.getWorkAreaById(workAreaIdCheck);
                Room room = new Room(idCheck, numberWithoutSpaces, workAreaList.get(0));
                roomService.updateRoom(room);
                List<Room> roomList = roomService.findAllRoom();
                model.addAttribute("roomList", roomList);
            }
            return this.getRoom(model);
        } catch (Exception e) {
            System.out.println("*** WRONG ID OR NUMBER TYPE***");
            return this.getRoom(model);
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
                if (roomList.isEmpty()) {
                    return this.getRoom(model);
                }
            }
            return "room";
        } catch (Exception e) {
            System.out.println("*** WRONG ID OR NUMBER TYPE***");
            return this.getRoom(model);
        }
    }
}
