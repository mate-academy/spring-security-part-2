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

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
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
                .mvcMatchers("/register", "movie-sessions/available")
                    .permitAll()
                .mvcMatchers("/movie-sessions/**", "/users/**")
                    .hasRole(RoleName.ROLE_ADMIN.toString())
                .mvcMatchers("/orders/**", "/shopping-carts/**")
                    .hasRole(RoleName.ROLE_USER.toString())
                .mvcMatchers(HttpMethod.POST, "/cinema-halls", "/movies")
                    .hasRole(RoleName.ROLE_ADMIN.toString())
                .mvcMatchers(HttpMethod.GET, "/cinema-halls", "/movies")
                    .permitAll()
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
