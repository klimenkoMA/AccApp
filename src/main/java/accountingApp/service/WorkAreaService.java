package accountingApp.service;

import accountingApp.entity.WorkArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.repository.WorkAreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public void updateWorkArea(WorkArea workArea) {
        workAreaRepository.save(workArea);
    }

    public List<WorkArea> getWorkAreaById(int id) {
        return workAreaRepository.findAreaById(id);
    }

    public List<WorkArea> getWorkAreaByName(String name){
        List<WorkArea> workAreaList = workAreaRepository.findAll();
        List<WorkArea> cloneArea = new ArrayList<>();
        for (WorkArea d : workAreaList
        ) {
            if (d.getName().toLowerCase(Locale.ROOT)
                    .contains(name.toLowerCase(Locale.ROOT))) {
                cloneArea.add(d);
            }
        }
        if (!cloneArea.isEmpty()) {
            return cloneArea;
        } else {
            return workAreaRepository.findAll();
        }

    }
}
