package recproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recproj.entity.AegerCaptions;

import java.util.List;

@Repository
public interface AegerCaptionsRepository extends JpaRepository<AegerCaptions, Integer> {

	@Override
	List<AegerCaptions> findAll();

	List<AegerCaptions> findBycAeger(int id);

	@Override
	void deleteById(Integer integer);
}
