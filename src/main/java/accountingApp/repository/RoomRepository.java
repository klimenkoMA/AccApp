package accountingApp.repository;

import accountingApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Override
    List<Room> findAll();

    List<Room> findRoomById(int id);

    void deleteById(int id);
}
