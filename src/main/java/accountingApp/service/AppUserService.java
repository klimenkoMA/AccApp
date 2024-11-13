package accountingApp.service;

import accountingApp.entity.AppUser;
import accountingApp.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppUserService {

    final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser createUser(AppUser user){
        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        logger.warn("AppUser " + user.getUserName() + " created!");
        return appUserRepository.save(user);

    }

}
