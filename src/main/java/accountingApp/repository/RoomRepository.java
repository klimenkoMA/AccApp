package accountingApp.repository;


import accountingApp.entity.Room;
import accountingApp.entity.WorkArea;
import accountingApp.entity.dto.roomdto.MaxRoomCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Override
    List<Room> findAll();

    List<Room> findRoomById(int id);

    void deleteById(int id);

    @Query(value = "SELECT r from Room r where r.number = ?1")
    List<Room> findByNumber(String number);

    @Query(value = "SELECT r from Room r where r.workarea = ?1")
    List<Room> findByWorkArea(WorkArea workarea);

    @Query(value = "SELECT new accountingApp.entity.dto.roomdto.MaxRoomCountDTO(w.name ," +
            " COUNT(r.id))  " +
            "FROM Room r " +
            "JOIN r.workarea w " +
            "GROUP BY w.name")
    List<MaxRoomCountDTO> reportingNumbersOfMaxRoomWorkArea();
}
