package accountingApp.repository;

import accountingApp.entity.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevicesRepository extends JpaRepository<Devices, Integer> {
	@Override
	List<Devices> findAll();

	List<Devices> findByid(int id);

	List<Devices> findByName(String name);

	@Override
	void deleteById(Integer integer);
}
