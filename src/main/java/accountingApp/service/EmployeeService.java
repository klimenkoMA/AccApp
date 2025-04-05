package accountingApp.service;

import accountingApp.entity.ITStaff;
import accountingApp.entity.Profession;
import accountingApp.entity.dto.employeedto.MaxEmployeesInWorkAreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Employee;
import accountingApp.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public List<Employee> findEmployeeByFio(String fio) {
        List<Employee> employeeList = employeeRepository.findAll();
        List<Employee> cloneEmployee = new ArrayList<>();
        for (Employee d : employeeList
        ) {
            if (d.getFio().toLowerCase(Locale.ROOT)
                    .contains(fio.toLowerCase(Locale.ROOT))) {
                cloneEmployee.add(d);
            }
        }
        if (!cloneEmployee.isEmpty()) {
            return cloneEmployee;
        } else {
            return employeeRepository.findAll();
        }
    }

    public List<Employee> findEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findEmployeeListByProfession(Profession profession) {
        return employeeRepository.findByProfession(profession);
    }

    public List<MaxEmployeesInWorkAreaDTO> getEmployeesCount(){
        return employeeRepository.reportingMaxEmployeesCountInWorkArea();
    }
}
