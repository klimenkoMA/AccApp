package accountingApp.repository;

import accountingApp.entity.ITStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITStaffRepository extends JpaRepository<ITStaff, Integer> {
	@Override
	List<ITStaff> findAll();

	List<ITStaff> findITStaffById(int id);

	@Override
	void deleteById(Integer integer);

}
