package recproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import recproj.entity.Persons;

import java.util.List;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {

    //	@Query("SELECT c FROM users c")
    List<Persons> findAll();

    List<Persons> findByFio(String fio);

    List<Persons> findById(int id);

    void deleteById(int id);
}
