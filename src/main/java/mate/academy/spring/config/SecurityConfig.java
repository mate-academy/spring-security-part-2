package mate.academy.spring.config;

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
                .antMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/movies",
                        "/movie-sessions",
                        "/cinema-halls").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls",
                        "/movie-sessions/available",
                        "/movies",
                        "/movie-sessions/{id}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/users/by-email").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/shopping-cart/by-user", "/orders").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/orders/complete").hasAuthority("USER")
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
