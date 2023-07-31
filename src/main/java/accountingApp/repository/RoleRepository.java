package accountingApp.repository;

import accountingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
