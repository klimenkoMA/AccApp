package accountingApp.repository;

import accountingApp.entity.Act;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRepository extends JpaRepository<Act, Long> {

}