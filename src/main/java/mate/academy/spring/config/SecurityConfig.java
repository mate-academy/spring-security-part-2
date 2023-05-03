package mate.academy.spring.config;

import mate.academy.spring.security.CustomUserDetailsService;
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

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls").authenticated()
                .antMatchers(HttpMethod.POST, "/cinema-halls").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/movies").authenticated()
                .antMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/movie-sessions/available").authenticated()
                .antMatchers(HttpMethod.POST, "/movie-session").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/movie-session/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/movie-session/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/users/by-user").hasRole("USER")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
