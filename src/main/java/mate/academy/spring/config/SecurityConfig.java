package mate.academy.spring.config;

import mate.academy.spring.model.RoleName;
import mate.academy.spring.security.CustomUserDetailsService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String ADMIN_ROLE = RoleName.ADMIN.toString();
    public static final String USER_ROLE = RoleName.USER.toString();
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions/available",
                        "/movie-sessions/{id}",
                        "/users/by-email").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET,
                        "/orders",
                        "/shopping-carts/by-user").hasRole(USER_ROLE)
                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole(USER_ROLE)
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasRole(USER_ROLE)
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
