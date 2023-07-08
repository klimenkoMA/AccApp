package accountingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import accountingApp.entity.ProfAegers;

import java.util.List;

@Repository
public interface ProfAegersRepository extends JpaRepository<ProfAegers, Integer> {

	@Override
	List<ProfAegers> findAll();

	List<ProfAegers> findById(int id);

	@Override
	void deleteById(Integer integer);
}
