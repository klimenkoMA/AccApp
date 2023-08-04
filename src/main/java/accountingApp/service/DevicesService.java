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

	public List<Devices> findAllDevices() {
		return devicesRepository.findAll();
	}

	public void addNewDevice(Devices devices) {
		devicesRepository.save(devices);
	}

	public void deleteDeviceById(Integer id) {
		devicesRepository.deleteById(id);
	}

	public void updateDevice(Devices devices) {
		devicesRepository.save(devices);
	}

	public List<Devices> getDevicesById(int id) {
		return devicesRepository.findByid(id);
	}
}
