package mate.academy.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.GET, "/movies", "/cinema-halls",
                        "/movie-sessions/available").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user/*",
                        "/orders").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/orders/complete",
                        "/shopping-carts/movie-sessions").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/cinema-halls",
                        "/movies", "/movie-sessions").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/movie-sessions").hasRole("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
