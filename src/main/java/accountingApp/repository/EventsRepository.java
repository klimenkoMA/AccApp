package accountingApp.repository;

import accountingApp.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Events, Integer> {
	@Override
	List<Events> findAll();

	List<Events> findById(int id);

	@Override
	void deleteById(Integer integer);
}
