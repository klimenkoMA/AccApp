package accountingApp.repository;

import accountingApp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    @Override
    List<Repair> findAll();

    List<Repair> findRepairsById(long id);

    void deleteById(long id);

    @Query(value = "select r from Repair r where r.firstDay = ?1")
    List<Repair> findRepairByFirstDay(String firstDay);

    @Query(value = "select r from Repair r where r.lastRepairDay = ?1")
    List<Repair> findRepairByLastRepairDay(String lastRepairDay);

    @Query(value = "select r from Repair r where r.repairCount = ?1")
    List<Repair> findRepairByRepairCount(int repairCount);

    @Query(value = "select r from Repair r where r.device = ?1")
    List<Repair> findRepairByDevice(Devices device);

    @Query(value = "select r from Repair r where r.category = ?1")
    List<Repair> findRepairByDeviceCategory(DeviceCategory category);

    @Query(value = "select r from Repair r where r.durability = ?1")
    List<Repair> findRepairByDurability(int durability);
}
