package accountingApp.repository;

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
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ITStaffRepository ITStaffRepository;
	@Autowired
	TestEntityManager testEntityManager;

	@Test
	public void createPersonsProf() {
		ITStaff ITStaff = new ITStaff("Плотник");
		ITStaffRepository.save(ITStaff);
	}
}
