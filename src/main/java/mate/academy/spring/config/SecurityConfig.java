package mate.academy.spring.config;

import mate.academy.spring.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/inject","/register").permitAll()
                .antMatchers(HttpMethod.POST, "/cinema-halls",
                        "/movies",
                        "/movie-sessions").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cinema-halls",
                        "/movies",
                        "/movie-sessions/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders").hasRole("USER")
                .antMatchers("/orders/complete",
                        "/shopping-carts/**").hasRole("USER")
                .antMatchers("/users/by-email").hasRole("ADMIN")
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
