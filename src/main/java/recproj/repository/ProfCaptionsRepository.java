package recproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recproj.entity.ProfCaptions;

import java.util.List;

@Repository
public interface ProfCaptionsRepository extends JpaRepository<ProfCaptions, Integer> {
	@Override
	List<ProfCaptions> findAll();

	List<ProfCaptions> findBycProf(int id);

	@Override
	void deleteById(Integer integer);
}
