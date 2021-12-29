package mate.academy.spring.config;

import mate.academy.spring.service.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder encoder;
    private final UserDetailService userDetailService;

    @Autowired
    public SecurityConfig(PasswordEncoder encoder, UserDetailService userDetailService) {
        this.encoder = encoder;
        this.userDetailService = userDetailService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(encoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/inject").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls",
                        "/movies",
                        "/movie-sessions/available",
                        "/movie-sessions/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/cinema-halls",
                        "/movies",
                        "/movie-sessions",
                        "/users/by-email").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders",
                        "/shopping-carts/by-user").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/orders/complete").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasAuthority("USER")
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
