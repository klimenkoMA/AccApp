package accountingApp.repository;

import accountingApp.entity.*;
import accountingApp.entity.dto.devicesdto.MaxOwnerCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс для связи с таблицей оборудования (Devices) в БД
 */

@Repository
public interface DevicesRepository extends JpaRepository<Devices, Integer> {
    @Override
    List<Devices> findAll();

    List<Devices> findByid(int id);

    List<Devices> findByName(String name);

    @Override
    void deleteById(Integer integer);

    @Query(value = "SELECT d from Devices d where d.category = ?1")
    List<Devices> findByCategory(DeviceCategory category);

    @Query(value = "SELECT d from Devices d where d.description = ?1")
    List<Devices> findByDescription(String description);

    @Query(value = "SELECT d from Devices d where d.inventory = ?1")
    List<Devices> findByInventory(Long inventory);

    @Query(value = "SELECT d from Devices d where d.serial = ?1")
    List<Devices> findBySerial(String serial);

    @Query(value = "SELECT d from Devices d where d.room = ?1")
    List<Devices> findByRoom(Room room);

    @Query(value = "SELECT d from Devices d where d.employee = ?1")
    List<Devices> findByEmployee(Employee employee);

    @Query(value = "SELECT d from Devices d where d.itstaff = ?1")
    List<Devices> findByItStaff(ITStaff itstaff);

    @Query(value = "SELECT d from Devices d where d.repair = ?1")
    List<Devices> findByRepair(Repair repair);

    @Query(value = "select new accountingApp.entity.dto.devicesdto.MaxOwnerCountDTO(" +
            " i.name, count(i.id)) " +
            " from Devices  d" +
            " join d.itstaff i" +
            " group by i.name")
    List<MaxOwnerCountDTO> reportingDevicesMaxOwnerCount();
}
