package mate.academy.spring.config;

import mate.academy.spring.model.RoleName;
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

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/inject").permitAll()
                .antMatchers(HttpMethod.GET, "/movies", "/cinema-halls",
                        "/movie-sessions/*").permitAll()
                .antMatchers(HttpMethod.GET,"/users/by-email").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/movies", "/cinema-halls",
                        "/movie-sessions").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/movie-sessions/*").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/*").hasRole(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/orders",
                        "/shopping-carts/by-user").hasRole(RoleName.USER.name())
                .antMatchers(HttpMethod.POST, "/orders/complete",
                        "/shopping-carts/movie-sessions").hasRole(RoleName.USER.name())
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
