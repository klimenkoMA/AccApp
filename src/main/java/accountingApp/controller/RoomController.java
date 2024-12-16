package accountingApp.controller;

import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.service.WorkAreaService;
import accountingApp.usefulmethods.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;
    @Autowired
    private WorkAreaService workAreaService;
    @Autowired
    private Checker checker;

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
                          @RequestParam (required = false) WorkArea workarea,
                          Model model) {

        if (checker.checkAttribute(number)
                || workarea == null
        ) {
            logger.warn("*** RoomController.addRoom():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String numberWithoutSpaces = number.trim();
        String workAreaIdWithoutSpaces = workarea.getName();

        try {
            int numberCheck = Integer.parseInt(numberWithoutSpaces);

            if (numberCheck <= 0) {
                logger.warn("*** RoomController.addRoom(): NUMBER is SUBZERO***");
                return getRoom(model);
            } else {
                List<WorkArea> workAreas = workAreaService
                        .getWorkAreaByName(workAreaIdWithoutSpaces);
                Room room = new Room(numberWithoutSpaces, workAreas.get(0));
                roomService.addNewRoom(room);
            }
            return getRoom(model);
        } catch (Exception e) {
            logger.error("*** RoomController.addRoom():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }
    }

    @PostMapping("/deleteroom")
    public String deleteRoom(@RequestParam String id,
                             Model model) {

        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** RoomController.deleteRoom():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** RoomController.deleteRoom(): NUMBER is SUBZERO***");
            } else {
                roomService.deleteRoomById(idCheck);
            }
            return getRoom(model);
        } catch (Exception e) {
            logger.error("*** RoomController.deleteRoom():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }

    }

    @PostMapping("/updateroom")
    public String updateRoom(@RequestParam String id,
                             @RequestParam String number,
                             @RequestParam (required = false) WorkArea workarea,
                             Model model) {

        if (checker.checkAttribute(id)
                || checker.checkAttribute(number)
                || workarea == null
        ) {
            logger.warn("*** RoomController.updateRoom():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String idWithoutSpaces = id.trim();
        String numberWithoutSpaces = number.trim();

        try {
            int workAreaId = workarea.getId();
            int idCheck = Integer.parseInt(idWithoutSpaces);
            int numberCheck = Integer.parseInt(numberWithoutSpaces);

            if (idCheck <= 0 || numberCheck <= 0) {
                logger.warn("*** RoomController.updateRoom(): NUMBER or ID is SUBZERO***");
            } else {
                List<WorkArea> workAreaList = workAreaService.getWorkAreaById(workAreaId);
                Room room = new Room(idCheck, numberWithoutSpaces, workAreaList.get(0));
                roomService.updateRoom(room);
            }
            return getRoom(model);
        } catch (Exception e) {
            logger.error("*** RoomController.updateRoom():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }
    }

    @PostMapping("/findroomyid")
    public String findRoomById(@RequestParam String id,
                               Model model) {

        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** RoomController.findRoomById():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String idWithoutSpaces = id.trim();
        try {
            int idCheck = Integer.parseInt(idWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("*** RoomController.findRoomById(): NUMBER or ID is SUBZERO***");
            } else {
                logger.debug("*** RoomController.findRoomById(): FOUND Room BY ID***");
                List<Room> roomList = roomService.getRoomById(idCheck);
                model.addAttribute("roomList", roomList);
                if (roomList.isEmpty()) {
                    return getRoom(model);
                }
            }
            return "room";
        } catch (Exception e) {
            logger.error("*** RoomController.findRoomById():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }
    }
}
