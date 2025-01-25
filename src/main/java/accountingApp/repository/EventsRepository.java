package accountingApp.repository;

import accountingApp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Events, Integer> {
    @Override
    List<Events> findAll();

    List<Events> findById(int id);

    @Override
    void deleteById(Integer integer);

    @Query(value = "select e from Events e where e.date = ?1")
    List<Events> findEventsByDate(String date);

    @Query(value = "select e from Events e where e.employeeid = ?1")
    List<Events> findEventsByEmployeeId(Employee employeeid);

    @Query(value = "select e from Events e where e.itstaffid = ?1")
    List<Events> findEventsByItStaffId(ITStaff itstaffid);

    @Query(value = "select e from Events e where e.workarea = ?1")
    List<Events> findEventsByWorkArea(WorkArea workarea);

    @Query(value = "select e from Events e where e.comment = ?1")
    List<Events> findEventsByComment(String comment);
}
