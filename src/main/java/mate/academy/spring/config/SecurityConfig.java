package mate.academy.spring.config;

import mate.academy.spring.model.Role;
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

    protected void configure(HttpSecurity http) throws Exception {
        String user = Role.RoleName.USER.name();
        String admin = Role.RoleName.ADMIN.name();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls").hasAnyRole(user, admin)
                .antMatchers(HttpMethod.POST, "/cinema-halls").hasRole(admin)
                .antMatchers(HttpMethod.GET, "/movies").hasAnyRole(user, admin)
                .antMatchers(HttpMethod.POST, "/movies").hasRole(admin)
                .antMatchers(HttpMethod.GET, "/movie-sessions/available").hasAnyRole(user, admin)
                .antMatchers(HttpMethod.POST, "/movie-sessions").hasRole(admin)
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}").hasRole(admin)
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}").hasRole(admin)
                .antMatchers(HttpMethod.GET, "/orders").hasRole(user)
                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole(user)
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions").hasRole(user)
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user").hasRole(user)
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(admin)
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
