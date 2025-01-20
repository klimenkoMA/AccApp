package accountingApp.service;

import accountingApp.entity.DeviceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Devices;
import accountingApp.repository.DevicesRepository;

import java.util.ArrayList;
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

    public List<Devices> getDevicesByName(String name) {
        List<Devices> devicesList = devicesRepository.findAll();
        List<Devices> cloneDevices = new ArrayList<>();
        for (Devices d : devicesList
        ) {
            if (d.getName().contains(name)) {
                cloneDevices.add(d);
            }
        }
        if (!cloneDevices.isEmpty()) {
            return cloneDevices;
        } else {
            return devicesRepository.findAll();
        }
    }

    public List<Devices> getDevicesById(int id) {
        return devicesRepository.findByid(id);
    }

    public List<Devices> getDevicesByCategory(DeviceCategory category) {
        return devicesRepository.findByCategory(category);
    }
}
