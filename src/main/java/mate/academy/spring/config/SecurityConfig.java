package mate.academy.spring.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService detailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService detailsService, PasswordEncoder passwordEncoder) {
        this.detailsService = detailsService;
        this.passwordEncoder = passwordEncoder;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/cinema-halls").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/movies").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/movie-sessions/**").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/movie-sessions/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/orders").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/orders/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/shopping-carts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/shopping-carts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/users/**").hasRole("ADMIN")
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
