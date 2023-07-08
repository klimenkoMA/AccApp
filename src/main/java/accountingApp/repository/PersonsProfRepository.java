package accountingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import accountingApp.entity.PersonsProf;

import java.util.List;

@Repository
public interface PersonsProfRepository extends JpaRepository<PersonsProf, Integer> {
	@Query("SELECT c FROM PersonsProf c")
	List<PersonsProf> findAll();

	List<PersonsProf> findPersonsProfById(int id);

	void deleteById(int id);

}
