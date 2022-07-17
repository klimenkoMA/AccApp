package recproj.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import recproj.entity.Persons;
import recproj.entity.PersonsProf;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PersonsRepositoryTest {

	@Autowired
	PersonsRepository personsRepository;
	@Autowired
	PersonsProfRepository personsProfRepository;
	@Autowired
	TestEntityManager testEntityManager;

	@Test
	public void createPersonsProf() {
		PersonsProf personsProf = new PersonsProf("Плотник");
		personsProfRepository.save(personsProf);
	}
}
