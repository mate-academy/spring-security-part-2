package mate.academy.spring.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
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

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
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
                .antMatchers("/register").permitAll()
                .antMatchers(GET, "/cinema-halls").hasAnyRole("USER", "ADMIN")
                .antMatchers(POST, "/cinema-halls").hasRole("ADMIN")
                .antMatchers(GET, "/movies").hasAnyRole("USER", "ADMIN")
                .antMatchers(POST, "/movies").hasRole("ADMIN")
                .antMatchers(GET, "/movie-sessions/available").hasAnyRole("USER", "ADMIN")
                .antMatchers(POST, "/movie-sessions").hasRole("ADMIN")
                .antMatchers(PUT, "/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(DELETE, "/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(GET, "/orders").hasRole("USER")
                .antMatchers(POST, "/orders/complete").hasRole("USER")
                .antMatchers(PUT, "/shopping-carts/movie-sessions").hasRole("USER")
                .antMatchers(GET, "/shopping-carts/by-user").hasRole("USER")
                .antMatchers(GET, "/users/by-email").hasRole("ADMIN")
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
