package accountingApp.repository;

import accountingApp.entity.*;
import accountingApp.entity.dto.employeedto.MaxEmployeesInWorkAreaDTO;
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

    @Query(value = "SELECT e from Employee e where e.dborn = ?1")
    List<Employee> findByDBorn(String dBorn);

    @Query(value = "SELECT e from Employee e where e.workarea = ?1")
    List<Employee> findByWorkArea(WorkArea workArea);

    @Query(value = "SELECT e from Employee e where e.room = ?1")
    List<Employee> findByRoom(Room room);

    @Query(value = "select" +
            " new accountingApp.entity.dto.employeedto.MaxEmployeesInWorkAreaDTO(" +
            " i.name, count(e.id))" +
            " from Employee e" +
            " join e.workarea i" +
            " group by i.name")
    List<MaxEmployeesInWorkAreaDTO> reportingMaxEmployeesCountInWorkArea();
}
