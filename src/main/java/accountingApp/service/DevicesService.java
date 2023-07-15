package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Devices;
import accountingApp.repository.DevicesRepository;

import java.util.List;

@Service
public class DevicesService {
	@Autowired
	DevicesRepository devicesRepository;

	public List<Devices> findAllProceduresAssigned() {
		return devicesRepository.findAll();
	}

	public void addNewProceduresAssigned(Devices devices) {
		devicesRepository.save(devices);
	}

	public void deleteProceduresAssignedById(int id) {
		devicesRepository.deleteById(id);
	}

	public void updateProceduresAssigned(Devices devices) {
		devicesRepository.save(devices);
	}

//	public List<Devices> getProceduresAssignedById(int id) {
//		return devicesRepository.findByid(id);
//	}
}
