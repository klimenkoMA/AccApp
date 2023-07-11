package accountingApp.service;

import accountingApp.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {
	@Autowired
	RoomRepository roomRepository;

	public List<Room> findAllProcedCaptions() {
		return roomRepository.findAll();
	}

	public void addNewProcedCaptions(Room room) {
		roomRepository.save(room);
	}

	public void deleteProcedCaptionsById(int id) {
		roomRepository.deleteById(id);
	}

	public void updateProcedCaptions(Room room) {
		roomRepository.save(room);
	}

	public List<Room> getProcedCaptionsById(int id) {
		return roomRepository.findProcedCaptionsBycProc(id);
	}
}
