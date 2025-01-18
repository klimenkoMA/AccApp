package accountingApp.repository;

import accountingApp.entity.DeviceCategory;
import accountingApp.entity.Devices;
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
}
