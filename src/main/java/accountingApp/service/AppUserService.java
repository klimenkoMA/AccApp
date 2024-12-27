package accountingApp.service;

import accountingApp.entity.AppUser;
import accountingApp.entity.Role;
import accountingApp.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService {

    private final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<AppUser> getAllAppUsers() {

        List<AppUser> appUserList = new ArrayList<>();

        try {
            if (appUserRepository.findAll() == null) {
                AppUser user = new AppUser(1, "test"
                        , "test", true,
                        new HashSet<>(Collections.singleton(Role.USER)) {
                        });
                appUserList.add(user);
                return appUserList;
            }
        } catch (Exception e) {
            logger.warn("***AppUserService.getAllAppUsers() appUserRepository.findAll()" +
                    "return "
                    + e.getMessage());
            AppUser user = new AppUser(0, "test"
                    , "test", true,
                    new HashSet<>(Collections.singleton(Role.USER)) {
                    });
            appUserList.add(user);
            return appUserList;
        }

        return setIsActiveFromBooleanIntoString(appUserRepository.findAll());
    }

    public AppUser createUser(AppUser user, String password) {
        user.setUserPass(passwordEncoder.encode(password));
        logger.warn("AppUser " + user.getUserName() + " created!");
        return appUserRepository.save(user);
    }

    public AppUser updateUser(AppUser user, String password) {
        user.setUserPass(passwordEncoder.encode(password));
        logger.warn("AppUser " + user.getUserName() + " updated!");
        return appUserRepository.save(user);
    }

    public List<AppUser> findUserById(long id) {
        return appUserRepository.findAppUserById(id);
    }

    public Optional<AppUser> findUserByName(String userName) {
        return appUserRepository.findByUserName(userName);
    }

    public void deleteUser(long id) {
        AppUser user = findUserById(id).get(0);
        logger.warn("AppUser " + user.getUserName() + " deleted!");
        appUserRepository.deleteById(id);
    }

    /**
     * Метод нужен для корректного отображения первых зарегистрированных пользователей.
     * Нужно убрать перед релизом и очисткой БД
     *
     * @param outerUserList
     * @return
     */
    private List<AppUser> setIsActiveFromBooleanIntoString(List<AppUser> outerUserList) {
        try {
            if (outerUserList == null) {
                throw new Exception("OuterUserList is NULL");
            }

            List<AppUser> innerUserList = new ArrayList<>();
            for (AppUser outerUser : outerUserList) {
                AppUser innerUser;

                long userId = outerUser.getId();
                String userName = outerUser.getUserName();
                Set<Role> userRoles = outerUser.getRoles();

                innerUser = new AppUser(userId,
                        userName,
                        outerUser.getUserPass(),
                        outerUser.isActive(),
                        userRoles);
                innerUserList.add(innerUser);
            }
            return innerUserList;
        } catch (Exception e) {
            logger.error("AppUserService.setIsActiveFromBooleanIntoString(): " +
                    e.getMessage());
            return null;
        }
    }

}
