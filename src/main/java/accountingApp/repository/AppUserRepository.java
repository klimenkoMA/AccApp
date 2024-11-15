package accountingApp.repository;

import accountingApp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

    List<AppUser> findByUserName(String userName);

    List<AppUser> findAppUserById(long id);

    void deleteById(long id);
}
