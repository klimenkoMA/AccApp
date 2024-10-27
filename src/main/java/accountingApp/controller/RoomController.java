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
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("roomList", roomList);
        model.addAttribute("workAreaList", workAreaList);
        return "room";
    }

    @PostMapping("/addroom")
    public String addRoom(@RequestParam String number,
                          @RequestParam WorkArea workarea,
                          Model model) {

        String numberWithoutSpaces = number.trim();
        String workAreaIdWithoutSpaces = workarea.getName();

        try {
            int numberCheck = Integer.parseInt(numberWithoutSpaces);

            if (numberCheck <= 0 ) {
                System.out.println("*** SUB ZERO NUMBER OR WORKAREA ID***");
                return this.getRoom(model);
            } else {
                List<WorkArea> workAreas = workAreaService
                        .getWorkAreaByName(workAreaIdWithoutSpaces);
                Room room = new Room(numberWithoutSpaces, workAreas.get(0));
                roomService.addNewRoom(room);
                List<Room> roomList = roomService.findAllRoom();

                model.addAttribute("roomList", roomList);
            }
            return this.getRoom(model);
        } catch (Exception e) {
            System.out.println("*** WRONG NUMBER TYPE***\n" + e.getMessage());
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
            System.out.println("*** WRONG ID TYPE***\n" + e.getMessage());
            return this.getRoom(model);
        }

    }

    @PostMapping("/updateroom")
    public String updateRoom(@RequestParam String id,
                             @RequestParam String number,
                             @RequestParam WorkArea workarea,
                             Model model) {
        String idWithoutSpaces = id.trim();
        String numberWithoutSpaces = number.trim();
        int workAreaId = workarea.getId();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            int numberCheck = Integer.parseInt(numberWithoutSpaces);

            if (idCheck <= 0 || numberCheck <= 0) {
                System.out.println("*** SUB ZERO ID OR NUMBER***");
            } else {
                List<WorkArea> workAreaList = workAreaService.getWorkAreaById(workAreaId);
                Room room = new Room(idCheck, numberWithoutSpaces, workAreaList.get(0));
                roomService.updateRoom(room);
                List<Room> roomList = roomService.findAllRoom();
                model.addAttribute("roomList", roomList);
            }
            return this.getRoom(model);
        } catch (Exception e) {
            System.out.println("*** WRONG ID OR NUMBER TYPE***\n" + e.getMessage());
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
            System.out.println("*** WRONG ID OR NUMBER TYPE***\n" + e.getMessage());
            return this.getRoom(model);
        }
    }
}
