package accountingApp.repository;

import accountingApp.entity.ITStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITStaffRepository extends JpaRepository<ITStaff, Integer> {
//	@Query("SELECT c FROM ITStaff c")
	List<ITStaff> findAll();

	List<ITStaff> findPersonsProfById(int id);

	void deleteById(int id);

}
