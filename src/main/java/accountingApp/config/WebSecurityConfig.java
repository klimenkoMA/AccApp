package accountingApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/video/**").permitAll()
                .antMatchers("/feedbacks").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        // Проверяем, есть ли у пользователя роль ADMIN
                        if (request.isUserInRole("ADMIN")) {
                            response.sendRedirect("/feedbacks");
                        } else {
                            response.sendRedirect("/");
                        }
                    })
                    .authenticationEntryPoint((request, response, authException) ->{
                        response.sendRedirect("/login");
                    })
                    .defaultAuthenticationEntryPointFor((request, response, authException) -> {
                        response.sendRedirect("/");
                    }, new AntPathRequestMatcher("/**"))
                    .and()
                    .csrf().disable();
    }

//    @Bean
//    @Override
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
//        return new InMemoryUserDetailsManager(users);
//    }


}