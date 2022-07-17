package recproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recproj.entity.ProceduresAssigned;

import java.util.List;

@Repository
public interface ProceduresAssignedRepository extends JpaRepository<ProceduresAssigned, Integer> {
	@Override
	List<ProceduresAssigned> findAll();

	List<ProceduresAssigned> findByid(int id);

	@Override
	void deleteById(Integer integer);
}
