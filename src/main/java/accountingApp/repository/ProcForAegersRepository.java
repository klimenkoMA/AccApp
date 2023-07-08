package accountingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import accountingApp.entity.ProcForAegers;

import java.util.List;

@Repository
public interface ProcForAegersRepository extends JpaRepository<ProcForAegers, Integer> {
	@Override
	List<ProcForAegers> findAll();

	List<ProcForAegers> findById(int id);

	@Override
	void deleteById(Integer integer);
}
