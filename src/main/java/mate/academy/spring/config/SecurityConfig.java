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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls", "/movies",
                        "/movie-sessions/available", "/movie-sessions/{id}")
                .hasAnyAuthority(RoleName.ADMIN.name(), RoleName.USER.name())
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls", "/movies", "/movie-sessions")
                .hasAnyAuthority(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.PUT,
                        "/movie-sessions/{id}")
                .hasAnyAuthority(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,
                        "/movie-sessions/{id}")
                .hasAnyAuthority(RoleName.ADMIN.name())
                .antMatchers(HttpMethod.GET,
                        "/orders", "/shopping-carts/by-user")
                .hasAnyAuthority(RoleName.USER.name())
                .antMatchers(HttpMethod.POST,
                        "/orders/complete")
                .hasAnyAuthority(RoleName.USER.name())
                .antMatchers(HttpMethod.PUT,
                        "/shopping-carts/movie-sessions")
                .hasAnyAuthority(RoleName.USER.name())
                .antMatchers(HttpMethod.GET,
                        "/users/by-email")
                .hasAnyAuthority(RoleName.ADMIN.name())
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
