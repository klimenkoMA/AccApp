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

	public void addNewEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
	}

	public List<Employee> getListEmployee() {
		return employeeRepository.findAll();
	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

//	public List<Employee> findEmployeeByFio(String fio) {
//		return employeeRepository.findByFio(fio);
//	}

//	public List<Employee> findEmployeeById(int id) {
//		return employeeRepository.findById(id);
//	}
}
