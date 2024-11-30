package accountingApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/video/**").permitAll()
                .antMatchers("/feedbacks").hasRole("ADMIN")
                .antMatchers("/users").hasRole("ADMIN")
//                .antMatchers("/feedbacks").hasRole("ADMIN")
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
                    System.out.println(request.getAuthType());
                    if (request.isUserInRole("ADMIN")) {
                        if (request.getRequestURI().contains("/feedbacks")) {
                            response.sendRedirect("/feedbacks");
                        } else if (request.getRequestURI().contains("users")) {
                            response.sendRedirect("/users");
                        }

                    } else {
                        response.sendRedirect("/");
                    }
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login");
                })
                .defaultAuthenticationEntryPointFor((request, response, authException) -> {
                    response.sendRedirect("/");
                }, new AntPathRequestMatcher("/**"))
                .and()
                .csrf().disable();
    }
}