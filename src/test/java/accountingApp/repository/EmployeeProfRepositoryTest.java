package accountingApp.repository;

import accountingApp.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import accountingApp.entity.PersonsProf;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmployeeProfRepositoryTest {

	@Autowired
	PersonsProfRepository personsProfRepository;
	@Autowired
	TestEntityManager testEntityManager;

	@Test
	public void createPersonsProf() {
		PersonsProf personsProf1 = new PersonsProf("Уролог");
		PersonsProf personsProf2 = new PersonsProf("Уфолог");
		PersonsProf personsProf3 = new PersonsProf("Гинеколог");
		PersonsProf personsProf4 = new PersonsProf("Терапевт");
		PersonsProf personsProf5 = new PersonsProf("Физиотерапевт");
		PersonsProf personsProf6 = new PersonsProf("Офтальмолог");
		PersonsProf personsProf7 = new PersonsProf("Лор");
		PersonsProf personsProf8 = new PersonsProf("Хирург");
		PersonsProf personsProf9 = new PersonsProf("Проктолог");
		PersonsProf personsProf10 = new PersonsProf("Дерматолог");
		personsProfRepository.save(personsProf1);
		personsProfRepository.save(personsProf2);
		personsProfRepository.save(personsProf3);
		personsProfRepository.save(personsProf4);
		personsProfRepository.save(personsProf5);
		personsProfRepository.save(personsProf6);
		personsProfRepository.save(personsProf7);
		personsProfRepository.save(personsProf8);
		personsProfRepository.save(personsProf9);
		personsProfRepository.save(personsProf10);
	}

	@Test
	public void createPersons() {
		PersonsProf personsProf = testEntityManager.find(PersonsProf.class, 1);
		Employee employee1 = new Employee("Петров Иван Иванович", "log1", "pass1", personsProf);
		Employee employee2 = new Employee("Мельников Илья Петрович", "log3", "pass3", personsProf);
		Employee employee3 = new Employee("Савва Георгий Олегович", "log4", "pass4", personsProf);
		Employee employee4 = new Employee("Куницина Валентина Петровна", "log2", "pass2", personsProf);
		personsProf.addPersons(employee1);
		personsProf.addPersons(employee2);
		personsProf.addPersons(employee3);
		personsProf.addPersons(employee4);
		personsProfRepository.save(personsProf);
	}
}
