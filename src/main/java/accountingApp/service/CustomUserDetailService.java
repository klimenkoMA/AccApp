package accountingApp.service;

import accountingApp.entity.AppUser;
import accountingApp.entity.Role;
import accountingApp.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
class CustomUserDetailsService implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {

            try{
                List<AppUser> appUserList = appUserService.getAllAppUsers();
                for (AppUser user: appUserList
                     ) {
                    if (user.getUserName().equals("admin")){
                        throw new Exception();
                    }
                }

                Set<Role> roleSet = new HashSet<>();
                roleSet.add(Role.ADMIN);
                roleSet.add(Role.USER);
                AppUser admin = new AppUser("admin", "1", true, roleSet );
                appUserService.createUser(admin, "1");
            }catch (Exception exception){
                logger.warn("CustomUserDetailsService.loadUserByUsername: INITIALISATION");
            }

            // Получаем пользователя из репозитория
            AppUser appUser = userRepository.findByUserName(userName).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

            if (!appUser.isActive()) {
                throw new Exception("User " + appUser.getUserName() + " isn't active!");
            }

            // Логируем успешную авторизацию
            logger.warn("CustomUserDetailsService.loadUserByUsername " +
                    "Successful authorization with user: " + userName);

            String roles = "";
            synchronized (roles) {
                if (appUser.getRoles().isEmpty()) {
                    roles = "USER";
                } else {
                    roles = appUser
                            .getRoles()
                            .stream()
                            .iterator()
                            .next()
                            .getAuthority();
                }

                if (appUser.getUserName().equals("admin")) {
                    roles = "ADMIN";
                }
                logger.warn("CustomUserDetailsService.loadUserByUsername " +
                        "User's role is : " + roles);

                // Создаем UserDetails
                return User
                        .builder()
                        .username(appUser.getUserName())
                        .password(appUser.getUserPass())
                        .roles(roles)
                        .build();
            }
        } catch (UsernameNotFoundException e) {
            logger.error("User not found: " + userName, e);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while loading user by username: " + userName, e);
            throw new UsernameNotFoundException(e.toString());
        }
    }

    /**
     * Метод для шифрования паролей всех пользователей, находящихся в БД на
     * данный момент. Применялся 1 раз, по окончанию тестирования работы системы
     * безопасности аутентификации
     */
    private void bCryptEncode() {

        List<AppUser> appUserList = appUserService.getAllAppUsers();
        AppUser user;
        String password;

        for (AppUser appUser : appUserList) {
            user = appUser;
            password = user.getUserPass();
            user.setUserPass(passwordEncoder.encode(password));
            userRepository.save(user);
        }

    }


}
