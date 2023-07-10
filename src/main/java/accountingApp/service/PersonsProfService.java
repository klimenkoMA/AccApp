package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Employee;
import accountingApp.entity.PersonsProf;
import accountingApp.repository.PersonsProfRepository;
import accountingApp.repository.EmployeeRepository;

import java.util.List;

@Service
public class PersonsProfService {
	@Autowired
	PersonsProfRepository personsProfRepository;
	@Autowired
	EmployeeRepository employeeRepository;


	public List<PersonsProf> getAllPersonsProf() {
		return personsProfRepository.findAll();
	}

	public void addNewPersonsProf(PersonsProf personsProf){
		personsProfRepository.save(personsProf);
	}

	public void deletePersonsProfById(int id){
		personsProfRepository.deleteById(id);
	}

	public void updatePersonsProf(PersonsProf personsProf){
		personsProfRepository.save(personsProf);
	}

	public List<PersonsProf> getPersonsProfById(int id) {
		return personsProfRepository.findPersonsProfById(id);
	}

	public void addNewPersons(Employee employee) {
		employeeRepository.save(employee);
	}
}
