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


@Service
class CustomUserDetailsService implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            // Получаем пользователя из репозитория
            AppUser appUser = userRepository.findByUserName(userName).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

            // Логируем успешную авторизацию
            logger.warn("Successful authorization with user: " + userName);

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
//                    .withDefaultPasswordEncoder()
                    .builder()
                    .username(appUser.getUserName())
                    .password(appUser.getUserPass())
                    .roles(roles) // Это можно заменить на получение ролей из `appUser`
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
