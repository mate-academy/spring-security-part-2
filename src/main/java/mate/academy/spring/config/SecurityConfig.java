package mate.academy.spring.config;

import mate.academy.spring.model.Role;
import mate.academy.spring.service.impl.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN = Role.RoleName.ADMIN.name();
    private static final String USER = Role.RoleName.USER.name();
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetails customUserDetails;

    public SecurityConfig(PasswordEncoder passwordEncoder, CustomUserDetails customUserDetails) {
        this.passwordEncoder = passwordEncoder;
        this.customUserDetails = customUserDetails;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetails).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/register/*").permitAll()
                .antMatchers(HttpMethod.POST,"/cinema-halls/*",
                        "/movies/*",
                        "/movies-sessions/*").hasRole(ADMIN)
                .antMatchers(HttpMethod.GET,"/movies/*",
                        "/cinema-halls/*",
                        "/movies-sessions/available/*").hasAnyRole(ADMIN, USER)
                .antMatchers(HttpMethod.GET,"/shopping-carts/by-user/*",
                        "/orders/*").hasRole(USER)
                .antMatchers(HttpMethod.PUT,"/movie-session/*").hasRole(ADMIN)
                .antMatchers(HttpMethod.DELETE,"/movies-session/*").hasRole(ADMIN)
                .antMatchers(HttpMethod.POST, "/orders/complete/*").hasRole(USER)
                .antMatchers(HttpMethod.PUT,"/shopping-carts/movie-sessions/*").hasRole(USER)
                .antMatchers(HttpMethod.GET,"/users/by-email/*").hasRole(ADMIN)
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
