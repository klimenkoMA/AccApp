package accountingApp.service;

import accountingApp.entity.AppUser;
import accountingApp.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class AppUserService {

    final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public AppUser createUser(AppUser user) {
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        logger.warn("AppUser " + user.getUserName() + " created!");
        return appUserRepository.save(user);
    }

    public AppUser updateUser(AppUser user) {
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
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

}
