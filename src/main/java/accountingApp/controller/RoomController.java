package accountingApp.controller;

import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.entity.dto.roomdto.MaxRoomCountDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
                          @RequestParam(required = false) WorkArea workarea,
                          @RequestParam String description,
                          Model model) {

        if (checker.checkAttribute(number)
                || workarea == null
        ) {
            logger.warn("*** RoomController.addRoom():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String numberWithoutSpaces = number.trim();
        String workAreaIdWithoutSpaces = workarea.getName();
        String descriptionWithoutSpaces = description.trim();

        try {
            int numberCheck = Integer.parseInt(numberWithoutSpaces);

            if (numberCheck <= 0) {
                logger.warn("*** RoomController.addRoom(): NUMBER is SUBZERO***");
                return getRoom(model);
            } else {
                List<WorkArea> workAreas = workAreaService
                        .getWorkAreaByName(workAreaIdWithoutSpaces);
                Room room = new Room(numberWithoutSpaces, workAreas.get(0), descriptionWithoutSpaces);
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
                List<Room> roomList = roomService.getRoomById(idCheck);

                Room room = roomList.get(0);
                room.setWorkarea(new WorkArea());
                roomService.updateRoom(room);

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
                             @RequestParam(required = false) WorkArea workarea,
                             @RequestParam String description,
                             Model model) {

        if (checker.checkAttribute(id)
                || checker.checkAttribute(number)
                || workarea == null
                || checker.checkAttribute(description)
        ) {
            logger.warn("*** RoomController.updateRoom():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String idWithoutSpaces = id.trim();
        String numberWithoutSpaces = number.trim();
        String descriptionWithoutSpaces = description.trim();

        try {
            int workAreaId = workarea.getId();
            int idCheck = Integer.parseInt(idWithoutSpaces);
            int numberCheck = Integer.parseInt(numberWithoutSpaces);

            if (idCheck <= 0 || numberCheck <= 0) {
                logger.warn("*** RoomController.updateRoom(): NUMBER or ID is SUBZERO***");
            } else {
                List<WorkArea> workAreaList = workAreaService.getWorkAreaById(workAreaId);
                Room room = new Room(idCheck, numberWithoutSpaces, workAreaList.get(0)
                        , descriptionWithoutSpaces);
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

    @PostMapping("/findroombynumber")
    public String findRoomByNumber(@RequestParam String number,
                                   Model model) {

        if (checker.checkAttribute(number)
        ) {
            logger.warn("*** RoomController.findRoomByNumber():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String numberWithoutSpaces = number.trim();
        try {

            logger.debug("*** RoomController.findRoomByNumber(): FOUND Room BY NUMBER ***");

            List<Room> roomList = roomService.getRoomByNumber(numberWithoutSpaces);
            model.addAttribute("roomList", roomList);
            if (roomList.isEmpty()) {
                return getRoom(model);
            }
            return "room";
        } catch (Exception e) {
            logger.error("*** RoomController.findRoomByNumber():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }
    }

    @PostMapping("/findroombyworkarea")
    public String findRoomByWorkArea(@RequestParam String workArea,
                                     Model model) {

        if (checker.checkAttribute(workArea)
        ) {
            logger.warn("*** RoomController.findRoomByNumber():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String workAreaWithoutSpaces = workArea.trim();


        try {

            WorkArea area = roomService.findAllRoom().stream()
                    .filter(r -> r.getWorkarea().getName().toLowerCase(Locale.ROOT)
                            .contains(workAreaWithoutSpaces.toLowerCase(Locale.ROOT)))
                    .findFirst()
                    .map(Room::getWorkarea)
                    .orElse(null);

            List<Room> roomList = roomService.getRoomByWorkArea(area);
            model.addAttribute("roomList", roomList);
            if (roomList.isEmpty()) {
                return getRoom(model);
            }
            return "room";
        } catch (Exception e) {
            logger.error("*** RoomController.findRoomByWorkArea():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }
    }

    @PostMapping("/findroombydescription")
    public String findRoomByDescription(@RequestParam String description,
                                        Model model) {

        if (checker.checkAttribute(description)
        ) {
            logger.warn("*** RoomController.findRoomByDescription():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String descriptionWithoutSpaces = description.trim();
        try {

            List<Room> roomList = roomService.findAllRoom().stream()
                    .filter(r -> r.getDescription().toLowerCase(Locale.ROOT)
                            .contains(descriptionWithoutSpaces.toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());

            model.addAttribute("roomList", roomList);
            if (roomList.isEmpty()) {
                return getRoom(model);
            }
            return "room";
        } catch (Exception e) {
            logger.error("*** RoomController.findRoomByDescription():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }
    }

    @PostMapping("/findroombyallattrs")
    public String findRoomByAllAttrs(@RequestParam String attrs,
                                     Model model) {

        if (checker.checkAttribute(attrs)
        ) {
            logger.warn("*** RoomController.findRoomByAllAttrs():  Attribute has a null value! ***");
            return getRoom(model);
        }

        String attrsWithoutSpaces = attrs.trim();
        try {
            List<Room> rooms = roomService.findAllRoom();
            List<Room> roomList = new ArrayList<>();

            for (Room r : rooms
            ) {
                if (r.getDescription().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces.toLowerCase(Locale.ROOT))) {
                    roomList.add(r);
                } else if (r.getWorkarea().getName().toLowerCase(Locale.ROOT)
                        .contains(attrs.toLowerCase(Locale.ROOT))) {
                    roomList.add(r);
                } else if (r.getNumber().contains(attrs)) {
                    roomList.add(r);
                }

            }

            model.addAttribute("roomList", roomList);
            if (roomList.isEmpty()) {
                logger.debug("*** RoomController.findRoomByAllAttrs():  DATA NOT FOUND IN DB*** "
                );
                return getRoom(model);
            }
            return "room";
        } catch (Exception e) {
            logger.error("*** RoomController.findRoomByAllAttrs():  WRONG DB VALUES*** "
                    + e.getMessage());
            return getRoom(model);
        }
    }

    @GetMapping("/maxroomscountreport")
    public String maxRoomsCountReport(Model model) {
        List<MaxRoomCountDTO> dtoList = roomService.getRoomsCount();

        String[] workAreaLabels = dtoList.stream()
                .map(MaxRoomCountDTO::getWorkAreaName)
                .toArray(String[]::new);

        long[] roomsCounts = dtoList.stream()
                .mapToLong(MaxRoomCountDTO::getRoomsCount)
                .toArray();

        model.addAttribute("dtoList", dtoList);
        model.addAttribute("workAreaLabels", workAreaLabels);
        model.addAttribute("roomsCounts", roomsCounts);

        return "/reports/roomreports/reportmaxroomcount";
    }


}
