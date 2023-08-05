package accountingApp.service;

import accountingApp.entity.Devices;
import accountingApp.entity.ITStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Employee;
import accountingApp.repository.ITStaffRepository;
import accountingApp.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ITStaffService {
    @Autowired
    ITStaffRepository ITStaffRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    public List<ITStaff> getAllItStaff() {
        return ITStaffRepository.findAll();
    }

    public void addNewItStaff(ITStaff ITStaff) {
        ITStaffRepository.save(ITStaff);
    }

    public void deleteITStaffById(int id) {
        ITStaffRepository.deleteById(id);
    }

    public void updateItStaff(ITStaff ITStaff) {
        ITStaffRepository.save(ITStaff);
    }

    public List<ITStaff> getITStaffById(int id) {
        return ITStaffRepository.findITStaffById(id);
    }

    public List<ITStaff> getITStaffByName(String name){

        List<ITStaff> itStaffList = ITStaffRepository.findAll();
        List<ITStaff> cloneITStaff = new ArrayList<>();
        for (ITStaff d : itStaffList
        ) {
            if (d.getName().equals(name)){
                cloneITStaff.add(d);
            }
        }
        if (!cloneITStaff.isEmpty()){
            return cloneITStaff;
        }else{
            return ITStaffRepository.findAll();
        }
    }

}
