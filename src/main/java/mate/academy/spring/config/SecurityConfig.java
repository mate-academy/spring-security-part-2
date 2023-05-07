package mate.academy.spring.config;

import javax.ws.rs.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(getEncoder())
                .withUser("user@i.ua").password(passwordEncoder.encode("user123")).roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET,"/cinema-halls").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/cinema-halls").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/movies").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/movies").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.GET,"/movie-sessions/available").hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST,"/movie-sessions").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/movie-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/movie-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/orders").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/orders/complete").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/shopping-carts/movie-sessions").hasRole("USER")
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

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
