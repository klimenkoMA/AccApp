package accountingApp.service;

import accountingApp.entity.AppUser;
import accountingApp.entity.Role;
import accountingApp.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
class CustomUserDetailsService implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    AppUserService appUserService;

    @Autowired
    private AppUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            // Получаем пользователя из репозитория
            AppUser appUser = userRepository.findByUserName(userName).orElseThrow(() ->
                    new UsernameNotFoundException("User not found"));

            // Логируем успешную авторизацию
            logger.warn("Successful authorization with user: " + userName);

            // Создаем UserDetails
            return User
                    .withDefaultPasswordEncoder()
                    .username(appUser.getUserName())
                    .password(appUser.getUserPass())
                    .roles("USER") // Это можно заменить на получение ролей из `appUser`
                    .build();
        } catch (UsernameNotFoundException e) {
            logger.error("User not found: " + userName, e);
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred while loading user by username: " + userName, e);
            throw new UsernameNotFoundException("User not found " + e.getMessage());
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//        try {
//            AppUser appUser = userRepository.findByUserName(userName).get(0);
//            if (appUser == null) {
//                throw new UsernameNotFoundException("User not found");
//            }
//
//            // Создайте UserDetails на основе информации о пользователе
//            logger.warn("Successful authorization with user: " + userName);
//
//            return User
//                    .withUsername(appUser.getUserName())
//                    .password(appUser.getUserPass())
//                    .roles("USER")
////                    .roles(appUser.getRoles()
////                            .stream()
////                            .map(Role::getAuthority).toArray(String[]::new))
//                    .build();
//        }catch (Exception e){
//            logger.error("User not found with name: " + userName + " " + e.getMessage());
//            throw new UsernameNotFoundException("User not found " + e.getMessage());
//        }
//    }

//        @Bean
////    @Override
//    public UserDetailsService userDetailsService() {
//        List<UserDetails> users = new ArrayList<>();
//        users.add(User.withDefaultPasswordEncoder()
//                .username("u")
//                .password("1")
//                .roles("USER")
//                .build());
//        users.add(User.withDefaultPasswordEncoder()
//                .username("u2")
//                .password("1")
//                .roles("USER")
//                .build());
//        users.add(User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("1")
//                .roles("ADMIN")
//                .build());
//
//        return new InMemoryUserDetailsManager();
//    }
}
