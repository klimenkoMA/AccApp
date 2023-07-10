package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Employee;
import accountingApp.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void deletePersonsById(int id) {
		employeeRepository.deleteById(id);
	}

	public List<Employee> getListPersons() {
		return employeeRepository.findAll();
	}

	public void updatePerson(Employee employee) {
		employeeRepository.save(employee);
	}

	public List<Employee> findPersonsByFio(String fio) {
		return employeeRepository.findByFio(fio);
	}

	public List<Employee> findPersonsById(int id) {
		return employeeRepository.findById(id);
	}
}
