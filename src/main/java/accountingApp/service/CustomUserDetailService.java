package accountingApp.service;

import accountingApp.entity.AppUser;
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

import java.util.List;


@Service
class CustomUserDetailsService implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void bCryptEncode(){

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


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            // Получаем пользователя из репозитория
            AppUser appUser = userRepository.findByUserName(userName).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

            // Логируем успешную авторизацию
            logger.warn("Successful authorization with user: " + userName);
//            bCryptEncode();

            String roles;

            if (appUser.getRoles().isEmpty()){
                roles = "USER";
            }else{
                roles = appUser
                        .getRoles()
                        .stream()
                        .iterator()
                        .next()
                        .getAuthority();
            }

            // Создаем UserDetails
            return User
                    .builder()
                    .username(appUser.getUserName())
                    .password(appUser.getUserPass())
                    .roles(roles)
                    .build();
        } catch (UsernameNotFoundException e) {
            logger.error("User not found: " + userName, e);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while loading user by username: " + userName, e);
            throw new UsernameNotFoundException("User not found " + e.getMessage());
        }
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }


}
