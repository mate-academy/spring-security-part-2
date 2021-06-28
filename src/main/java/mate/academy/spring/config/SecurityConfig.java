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
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.GET,"/movies/**").permitAll()
                .antMatchers(HttpMethod.GET,"/cinema-halls/**").permitAll()
                .antMatchers(HttpMethod.GET,"/movie-sessions/**").permitAll()
                .antMatchers(HttpMethod.POST,"/movies/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/cinema-halls/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/movie-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/orders/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/shopping-carts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/shopping-carts/**").hasRole("USER")
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
