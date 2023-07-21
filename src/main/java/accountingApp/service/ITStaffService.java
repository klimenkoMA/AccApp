package accountingApp.service;

import accountingApp.entity.ITStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Employee;
import accountingApp.repository.ITStaffRepository;
import accountingApp.repository.EmployeeRepository;

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

	public void addNewItStaff(ITStaff ITStaff){
		ITStaffRepository.save(ITStaff);
	}

	public void deletePersonsProfById(int id){
		ITStaffRepository.deleteById(id);
	}

	public void updatePersonsProf(ITStaff ITStaff){
		ITStaffRepository.save(ITStaff);
	}

//	public List<ITStaff> getPersonsProfById(int id) {
//		return ITStaffRepository.findPersonsProfById(id);
//	}

}
