package accountingApp.repository;

import accountingApp.entity.Employee;
import accountingApp.entity.ITStaff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmployeeProfRepositoryTest {

	@Autowired
	ITStaffRepository ITStaffRepository;
	@Autowired
	TestEntityManager testEntityManager;

//	@Test
//	public void createPersonsProf() {
//		ITStaff ITStaff1 = new ITStaff("Уролог");
//		ITStaff ITStaff2 = new ITStaff("Уфолог");
//		ITStaff ITStaff3 = new ITStaff("Гинеколог");
//		ITStaff ITStaff4 = new ITStaff("Терапевт");
//		ITStaff ITStaff5 = new ITStaff("Физиотерапевт");
//		ITStaff ITStaff6 = new ITStaff("Офтальмолог");
//		ITStaff ITStaff7 = new ITStaff("Лор");
//		ITStaff ITStaff8 = new ITStaff("Хирург");
//		ITStaff ITStaff9 = new ITStaff("Проктолог");
//		ITStaff ITStaff10 = new ITStaff("Дерматолог");
//		ITStaffRepository.save(ITStaff1);
//		ITStaffRepository.save(ITStaff2);
//		ITStaffRepository.save(ITStaff3);
//		ITStaffRepository.save(ITStaff4);
//		ITStaffRepository.save(ITStaff5);
//		ITStaffRepository.save(ITStaff6);
//		ITStaffRepository.save(ITStaff7);
//		ITStaffRepository.save(ITStaff8);
//		ITStaffRepository.save(ITStaff9);
//		ITStaffRepository.save(ITStaff10);
//	}

//	@Test
//	public void createPersons() {
//		ITStaff ITStaff = testEntityManager.find(ITStaff.class, 1);
//		Employee employee1 = new Employee("Петров Иван Иванович", "log1", "pass1", ITStaff);
//		Employee employee2 = new Employee("Мельников Илья Петрович", "log3", "pass3", ITStaff);
//		Employee employee3 = new Employee("Савва Георгий Олегович", "log4", "pass4", ITStaff);
//		Employee employee4 = new Employee("Куницина Валентина Петровна", "log2", "pass2", ITStaff);
//		ITStaff.addPersons(employee1);
//		ITStaff.addPersons(employee2);
//		ITStaff.addPersons(employee3);
//		ITStaff.addPersons(employee4);
//		ITStaffRepository.save(ITStaff);
//	}
}
