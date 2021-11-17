package mate.academy.spring.config;

import mate.academy.spring.model.RoleName;
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
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls").authenticated()
                .antMatchers(HttpMethod.POST, "/cinema-halls").hasRole(RoleName.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/movies").authenticated()
                .antMatchers(HttpMethod.POST, "/movies").hasRole(RoleName.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/movie-sessions/available").authenticated()
                .antMatchers(HttpMethod.GET, "/movie-sessions/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/movie-sessions").hasRole(RoleName.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}")
                .hasRole(RoleName.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}")
                .hasRole(RoleName.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/orders").hasRole(RoleName.USER.toString())
                .antMatchers(HttpMethod.POST, "/orders/complete").hasRole(RoleName.USER.toString())
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions")
                .hasRole(RoleName.USER.toString())
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user")
                .hasRole(RoleName.USER.toString())
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(RoleName.ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }
}
