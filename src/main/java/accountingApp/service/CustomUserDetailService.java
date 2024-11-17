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
import org.springframework.stereotype.Service;



@Service
class CustomUserDetailsService implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private AppUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        try {
            AppUser appUser = userRepository.findByUserName(userName).get(0);
            if (appUser == null) {
                throw new UsernameNotFoundException("User not found");
            }

            // Создайте UserDetails на основе информации о пользователе
            logger.warn("Successful authorization with user: " + userName);

            return User
                    .withUsername(appUser.getUserName())
                    .password(appUser.getUserPass())
                    .roles(String.valueOf(Role.USER))
//                    .roles(appUser.getRoles()
//                            .stream()
//                            .map(Role::getAuthority).toArray(String[]::new))
                    .build();
        }catch (Exception e){
            logger.error("User not found with name: " + userName + " " + e.getMessage());
            throw new UsernameNotFoundException("User not found " + e.getMessage());
        }
    }
}
