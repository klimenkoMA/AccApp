package recproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recproj.entity.RecreantsAegers;

import java.util.List;

@Repository
public interface RecreantsAegersRepository extends JpaRepository<RecreantsAegers, Integer> {
	@Override
	List<RecreantsAegers> findAll();

	List<RecreantsAegers> findById(int id);

	@Override
	void deleteById(Integer integer);
}
