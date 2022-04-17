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

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /*
GET: /orders - user
POST: /orders/complete - user
PUT: /shopping-carts/movie-sessions - user
GET: /shopping-carts/by-user - user
GET: /users/by-email - admin
     */
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers(HttpMethod.POST,
                        "/movies",
                        "/cinema-halls",
                        "/movie-sessions").hasRole("ADMIN")

                .antMatchers(HttpMethod.PUT,
                        "/movie-sessions/{id}").hasRole("ADMIN")

                .antMatchers(HttpMethod.DELETE,
                        "/movie-sessions/{id}").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,
                        "/shopping-carts",
                        "/orders/**").hasRole("USER")

                .antMatchers(HttpMethod.GET,
                        "/users/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,
                        "/register/**",
                        "/movies/**",
                        "/cinema-halls/**",
                        "/movie-sessions/available").permitAll()

                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
