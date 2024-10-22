package accountingApp.controller;

import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import accountingApp.service.WorkAreaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WorkAreaController {
    @Autowired
    WorkAreaService workAreaService;
    @Autowired
    RoomService roomService;


    @GetMapping("/workarea")
    public String getWorkArea(Model model) {
        List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
        model.addAttribute("workAreaList", workAreaList);
        return "workarea";
    }

    @PostMapping("/addworkarea")
    public String addWorkArea(@RequestParam String name,
                              @RequestParam String roomId,
                              Model model) {
        String nameWithoutSpaces = name.trim();
        String roomWithoutSpaces = roomId.trim();
        if (nameWithoutSpaces.equals("") || roomWithoutSpaces.equals("")
                || nameWithoutSpaces.equals(" ") || roomWithoutSpaces.equals(" ")) {
            return this.getWorkArea(model);
        }

        try {
            List<Room> roomList = roomService.getRoomById(Integer.parseInt(roomWithoutSpaces));
            WorkArea workArea = new WorkArea(nameWithoutSpaces, roomList.get(0));
            workAreaService.addNewWorkArea(workArea);

            List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
            model.addAttribute("workAreaList", workAreaList);
            return this.getWorkArea(model);
        }catch (Exception e){
            return this.getWorkArea(model);
        }
    }

    @PostMapping("/deleteworkarea")
    public String deleteWorkArea(@RequestParam String id,
                                 Model model) {
        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB ZERO ID***");
            } else {
                workAreaService.deleteWorkAreaById(idCheck);
                List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
                model.addAttribute("workAreaList", workAreaList);
            }
            return this.getWorkArea(model);

        } catch (Exception e) {
            System.out.println("*** WRONG ID TYPE ***");
            return this.getWorkArea(model);
        }
    }

    @PostMapping("/updateworkarea")
    public String updateWorkArea(@RequestParam String id,
                                 @RequestParam String name,
                                 @RequestParam String roomId,
                                 Model model) {
        String nameWithoutSpaces = name.trim();
        String roomWithoutSpaces = roomId.trim();
        String idWithoutSpaces = id.trim();

        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            int roomIdCheck = Integer.parseInt(roomWithoutSpaces);
            if (idCheck <= 0 || roomIdCheck <= 0) {
                System.out.println("*** SUB-ZERO ID OR ROOM ***");
            } else {
                if (nameWithoutSpaces.equals("") || roomWithoutSpaces.equals("")
                        || nameWithoutSpaces.equals(" ") || roomWithoutSpaces.equals(" ")) {
                    return this.getWorkArea(model);
                }
                List<Room> roomList = roomService.getRoomById(Integer.parseInt(roomWithoutSpaces));
                WorkArea workArea = new WorkArea(idCheck, nameWithoutSpaces, roomList.get(0));
                workAreaService.updateWorkArea(workArea);
                List<WorkArea> workAreaList = workAreaService.findAllWorkArea();
                model.addAttribute("workAreaList", workAreaList);
            }
            return this.getWorkArea(model);
        } catch (Exception e) {
            System.out.println("*** WRONG ID TYPE ***");
            return this.getWorkArea(model);
        }
    }

    @PostMapping("/findworkareabyname")
    public String findAreaByName(@RequestParam String name,
                                 Model model) {
        String nameWithoutSpaces = name.trim();
        try {
            int idCheck = Integer.parseInt(nameWithoutSpaces);
            if (idCheck <= 0) {
                System.out.println("*** SUB-ZERO ID ***");
            } else {
                System.out.println("*** FOUND BY ID ***");
                List<WorkArea> workAreaList;
                workAreaList = workAreaService.getWorkAreaById(idCheck);
                model.addAttribute("workAreaList", workAreaList);
            }
            return "workarea";
        } catch (Exception e) {
            System.out.println("*** FOUND BY NAME ***");
            List<WorkArea> workAreaList = workAreaService.getWorkAreaByName(name);

            if (workAreaList.isEmpty()) {
                return this.getWorkArea(model);
            }
            model.addAttribute("workAreaList", workAreaList);
            return "workarea";
        }

    }
}
