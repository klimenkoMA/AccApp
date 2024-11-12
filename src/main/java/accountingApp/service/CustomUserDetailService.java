package accountingApp.service;

import accountingApp.entity.AppUser;
import accountingApp.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            AppUser appUser = userRepository.findByUsername(username);
            if (appUser == null) {
                throw new UsernameNotFoundException("User not found");
            }

            // Создайте UserDetails на основе информации о пользователе
            return org.springframework.security.core.userdetails.User
                    .withUsername(appUser.getUserName())
                    .password(appUser.getUserPass())
                    .roles(appUser.getRoles().toString()) // предположим, что роли хранятся через запятую
                    .build();
        }catch (Exception e){
            logger.error("User not found " + e.getMessage());
            return null;
        }
    }
}
