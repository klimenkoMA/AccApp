package accountingApp.service;

import accountingApp.entity.ITStaff;
import accountingApp.entity.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.repository.ITStaffRepository;
import accountingApp.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ITStaffService {
    @Autowired
    ITStaffRepository iTStaffRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    public List<ITStaff> getAllItStaff() {
        return iTStaffRepository.findAll();
    }

    public void addNewItStaff(ITStaff ITStaff) {
        iTStaffRepository.save(ITStaff);
    }

    public void deleteITStaffById(int id) {
        iTStaffRepository.deleteById(id);
    }

    public void updateItStaff(ITStaff ITStaff) {
        iTStaffRepository.save(ITStaff);
    }

    public List<ITStaff> getITStaffById(int id) {
        return iTStaffRepository.findITStaffById(id);
    }

    public List<ITStaff> getITStaffByName(String name) {
        List<ITStaff> itStaffList = iTStaffRepository.findAll();
        List<ITStaff> cloneITStaff = new ArrayList<>();
        for (ITStaff d : itStaffList
        ) {
            if (d.getName().toLowerCase(Locale.ROOT)
                    .contains(name.toLowerCase(Locale.ROOT))) {
                cloneITStaff.add(d);
            }
        }
        if (!cloneITStaff.isEmpty()) {
            return cloneITStaff;
        } else {
            return iTStaffRepository.findAll();
        }
    }

    public List<ITStaff> getItStaffByProfession(Profession profession){
        return iTStaffRepository.findITStaffByProfession(profession);
    }

}
