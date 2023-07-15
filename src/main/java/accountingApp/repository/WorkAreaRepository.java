package accountingApp.repository;

import accountingApp.entity.WorkArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkAreaRepository extends JpaRepository<WorkArea, Integer> {

	@Override
	List<WorkArea> findAll();

//	List<WorkArea> findBycAeger(int id);

	@Override
	void deleteById(Integer integer);
}
