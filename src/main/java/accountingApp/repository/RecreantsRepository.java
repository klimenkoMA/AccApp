package accountingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import accountingApp.entity.Recreants;

import java.util.List;

@Repository
public interface RecreantsRepository extends JpaRepository<Recreants, Integer> {
	@Override
	List<Recreants> findAll();

	List<Recreants> findById(int id);

	@Override
	void deleteById(Integer integer);
}
