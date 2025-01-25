package accountingApp.repository;

import accountingApp.entity.Devices;
import accountingApp.entity.ITStaff;
import accountingApp.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITStaffRepository extends JpaRepository<ITStaff, Integer> {
    @Override
    List<ITStaff> findAll();

    List<ITStaff> findITStaffById(int id);

    @Override
    void deleteById(Integer integer);

    @Query(value = "select i from ITStaff i where i.profession = ?1")
    List<ITStaff> findITStaffByProfession(Profession profession);

    @Query(value = "select i from ITStaff i where i.name = ?1")
    List<ITStaff> findITStaffByName(String name);

}
