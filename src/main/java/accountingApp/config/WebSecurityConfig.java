package accountingApp.config;

import accountingApp.securityController.SecurityControllerClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final Logger logger = LoggerFactory.getLogger(SecurityControllerClass.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        synchronized (http) {
            http
                    .authorizeRequests()
                    .antMatchers("/resources/**", "/static/**", "/css/**", "/video/**", "/js/**").permitAll()
                    .antMatchers("/feedbacks").hasRole("ADMIN")
                    .antMatchers("/users").hasRole("ADMIN")
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
                        logger.warn("WebSecurityConfig: User's role is " +
                                request.getAuthType());
                        if (request.isUserInRole("ADMIN")) {
                            if (request.getRequestURI().contains("/feedbacks")) {
                                response.sendRedirect("/feedbacks");
                            } else if (request.getRequestURI().contains("users")) {
                                response.sendRedirect("/users");
                            }

                        } else {
                            response.sendRedirect("/main");
                        }
                    })
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.sendRedirect("/login");
                    })
                    .defaultAuthenticationEntryPointFor((request, response, authException) -> {
                        response.sendRedirect("/main");
                    }, new AntPathRequestMatcher("/**"))
                    .and()
                    .csrf().disable();
        }
    }
}