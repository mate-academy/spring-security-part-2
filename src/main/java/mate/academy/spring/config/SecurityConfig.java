package mate.academy.spring.config;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register", "/login").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions/**").hasAnyAuthority(Role.RoleType.ADMIN.name(),
                        Role.RoleType.USER.name())
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions").hasAuthority(Role.RoleType.ADMIN.name())
                .antMatchers(HttpMethod.PUT,
                        "/movie-sessions/{id}").hasAuthority(Role.RoleType.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,
                        "/movie-sessions/{id}").hasAuthority(Role.RoleType.ADMIN.name())
                .antMatchers(HttpMethod.GET,
                        "/orders",
                        "/shopping-carts/by-user").hasAuthority(Role.RoleType.USER.name())
                .antMatchers(HttpMethod.PUT,
                        "/shopping-carts/movie-sessions").hasAuthority(Role.RoleType.USER.name())
                .antMatchers(HttpMethod.POST,
                        "/orders/complete").hasAuthority(Role.RoleType.USER.name())
                .antMatchers(HttpMethod.GET,
                        "/users/by-email").hasAuthority(Role.RoleType.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}
