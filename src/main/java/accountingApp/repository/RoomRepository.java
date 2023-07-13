package accountingApp.repository;

import accountingApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findAll();

//	List<Room> findProcedCaptionsBycProc(int id);

	void deleteById(int id);
}
