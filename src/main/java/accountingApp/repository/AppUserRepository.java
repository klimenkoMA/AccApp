package accountingApp.repository;

import accountingApp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Override
    List<AppUser> findAll();

    Optional<AppUser> findByUserName(String userName);

    List<AppUser> findAppUserById(long id);

    void deleteById(long id);
}
