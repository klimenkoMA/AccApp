package accountingApp.repository;

import accountingApp.entity.DeviceCategory;
import accountingApp.entity.Devices;
import accountingApp.entity.Employee;
import accountingApp.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс для связи с таблицей сотрудников (Employees) в БД
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Override
    List<Employee> findAll();

    List<Employee> findByFio(String fio);

    List<Employee> findById(int id);

    void deleteById(int id);

    @Query(value = "SELECT e from Employee e where e.profession = ?1")
    List<Employee> findByProfession(Profession profession);
}
