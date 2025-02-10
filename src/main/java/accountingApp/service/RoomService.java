package accountingApp.service;

import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.entity.dto.roomdto.MaxRoomCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    public void addNewRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoomById(int id) {
        roomRepository.deleteById(id);
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    public List<Room> getRoomById(int id) {
        return roomRepository.findRoomById(id);
    }

    public List<Room> getRoomByNumber(String number) {
        return roomRepository.findByNumber(number);
    }

    public List<Room> getRoomByWorkArea(WorkArea workArea) {
        return roomRepository.findByWorkArea(workArea);
    }

    public List<MaxRoomCountDTO> getRoomsCount() {
        return roomRepository.reportingNumbersOfMaxRoomWorkArea();
    }

}
