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

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/register").permitAll()

                .antMatchers(HttpMethod.GET,"/cinema-halls/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/cinema-halls").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/movies/**").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET,"/movies").hasAnyRole("ADMIN", "USER")
                //.antMatchers(HttpMethod.POST,"/movies").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/movie-sessions/available").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/movie-sessions/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/movie-sessions").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/movie-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/movie-sessions/{id}").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/orders").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/orders/complete").hasRole("USER")

                .antMatchers(HttpMethod.POST,"/shopping-carts/movie-sessions").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/shopping-carts/by-user").hasRole("USER")

                .antMatchers(HttpMethod.GET,"/users/by-email").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    /*
GET: /cinema-halls - user/admin
POST: /cinema-halls - admin
GET: /movies - user/admin

POST: /movies - admin
GET: /movie-sessions/available - user/admin
GET: /movie-sessions/{id} - user/admin

POST: /movie-sessions - admin
PUT: /movie-sessions/{id} - admin
DELETE: /movie-sessions/{id} - admin

GET: /orders - user
POST: /orders/complete - user
POST: /shopping-carts/movie-sessions - user

GET: /shopping-carts/by-user - user
GET: /users/by-email - admin
     */
}
