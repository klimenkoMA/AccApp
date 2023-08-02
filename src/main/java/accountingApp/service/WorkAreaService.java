package accountingApp.service;

import accountingApp.entity.WorkArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.repository.WorkAreaRepository;

import java.util.List;

@Service
public class WorkAreaService {
	@Autowired
	WorkAreaRepository workAreaRepository;

	public List<WorkArea> findAllWorkArea() {
		return workAreaRepository.findAll();
	}

	public void addNewWorkArea(WorkArea workArea) {
		workAreaRepository.save(workArea);
	}

	public void deleteWorkAreaById(int id) {
		workAreaRepository.deleteById(id);
	}

	public void updateAegerCaptions(WorkArea workArea) {
		workAreaRepository.save(workArea);
	}

//	public List<WorkArea> getAegerCaptionsById(int id) {
//		return workAreaRepository.findBycAeger(id);
//	}
}
