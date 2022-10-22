package mate.academy.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/inject").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.POST, "/cinema-halls/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/cinema-halls/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/movies").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/movies").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/movie-sessions/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/movie-sessions/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/orders").hasAnyRole("USER")
                .antMatchers(HttpMethod.POST, "/orders/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/shopping-carts/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.POST, "/shopping-carts/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN")
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
