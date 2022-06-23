package mate.academy.spring.config;

import mate.academy.spring.model.Role;
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
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/login", "/inject").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions/**").hasAnyAuthority(String.valueOf(Role.RoleName.ADMIN),
                        String.valueOf(Role.RoleName.USER))
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions").hasAuthority(String.valueOf(Role.RoleName.ADMIN))
                .antMatchers(HttpMethod.PUT,
                        "/movie-sessions/{id}").hasAuthority(String.valueOf(Role.RoleName.ADMIN))
                .antMatchers(HttpMethod.DELETE,
                        "/movie-sessions/{id}").hasAuthority(String.valueOf(Role.RoleName.ADMIN))
                .antMatchers(HttpMethod.GET,
                        "/orders",
                        "/shopping-carts/by-user").hasAuthority(String.valueOf(Role.RoleName.USER))
                .antMatchers(HttpMethod.PUT,
                        "/shopping-carts/movie-sessions").hasAuthority(String.valueOf(Role
                        .RoleName.USER))
                .antMatchers(HttpMethod.POST,
                        "/orders/complete").hasAuthority(String.valueOf(Role.RoleName.USER))
                .antMatchers(HttpMethod.GET,
                        "/users/by-email").hasAuthority(String.valueOf(Role.RoleName.ADMIN))
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}
