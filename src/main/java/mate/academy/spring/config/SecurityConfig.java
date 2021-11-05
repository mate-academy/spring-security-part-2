package mate.academy.spring.config;

import static mate.academy.spring.model.Role.RoleName.ADMIN;
import static mate.academy.spring.model.Role.RoleName.USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").hasAnyRole(String.valueOf(ADMIN))
                .antMatchers(HttpMethod.GET, "/shopping-carts/**").hasAnyRole(
                        String.valueOf(ADMIN), String.valueOf(USER))
                .antMatchers(HttpMethod.PUT, "/shopping-carts/**").hasAnyRole(
                        String.valueOf(ADMIN), String.valueOf(USER))
                .antMatchers(HttpMethod.GET, "/orders").hasAnyRole(
                        String.valueOf(ADMIN))
                .antMatchers(HttpMethod.POST, "/orders/complete").hasAnyRole(
                        String.valueOf(ADMIN), String.valueOf(USER))
                .antMatchers(HttpMethod.GET, "/movie-sessions/**").hasAnyRole(
                        String.valueOf(ADMIN), String.valueOf(USER))
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/**").hasAnyRole(
                        String.valueOf(ADMIN))
                .antMatchers(HttpMethod.POST, "/movie-sessions/**").hasAnyRole(
                        String.valueOf(ADMIN))
                .antMatchers(HttpMethod.PUT, "/movie-sessions/**").hasAnyRole(
                        String.valueOf(ADMIN))
                .antMatchers(HttpMethod.GET,"/movies/**").fullyAuthenticated()
                .antMatchers(HttpMethod.POST,"/movies/**").hasRole(String.valueOf(ADMIN))
                .antMatchers(HttpMethod.GET,"/cinema-halls/**").fullyAuthenticated()
                .antMatchers(HttpMethod.POST,"/cinema-halls/**").hasRole(String.valueOf(ADMIN))
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
