package accountingApp.repository;

import accountingApp.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    @Override
    List<Repair> findAll();

    List<Repair> findRepairsById(long id);

    void deleteById(long id);
}
