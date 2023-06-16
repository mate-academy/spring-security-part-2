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
    private static final String USER = Role.RoleName.USER.name();
    private static final String ADMIN = Role.RoleName.ADMIN.name();
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder,
                          UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}")
                .hasRole(ADMIN)
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls", "/movie-sessions/available", "/movies")
                .hasAnyRole(ADMIN, USER)
                .antMatchers(HttpMethod.GET,
                        "/orders", "/shopping-carts/by-user",
                        "/orders/complete", "/shopping-carts/movie-sessions")
                .hasRole(USER)
                .antMatchers(HttpMethod.GET,
                        "/users/by-email", "/movie-sessions/{id}",
                        "/cinema-halls", "/movies", "/movie-sessions")
                .hasRole(ADMIN)
                .antMatchers(HttpMethod.POST, "/register")
                .permitAll()
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
