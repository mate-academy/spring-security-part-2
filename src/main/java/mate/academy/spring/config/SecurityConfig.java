package mate.academy.spring.config;

import mate.academy.spring.model.RoleType;
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
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.GET,"/movies").hasAnyAuthority(
                        RoleType.ADMIN.name(), RoleType.USER.name())
                .antMatchers(HttpMethod.POST,"/movies").hasAuthority(RoleType.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/cinema-halls").hasAuthority(RoleType.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/cinema-halls")
                .hasAnyAuthority(RoleType.ADMIN.name(), RoleType.USER.name())
                .antMatchers(HttpMethod.GET, "/movie-sessions/**")
                .hasAnyAuthority(RoleType.ADMIN.name(), RoleType.USER.name())
                .antMatchers("/movie-sessions/**").hasAuthority(RoleType.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/movie-sessions")
                .hasAuthority(RoleType.ADMIN.name())
                .antMatchers("/orders/**", "/shopping-carts/**").hasAuthority(RoleType.USER.name())
                .antMatchers(HttpMethod.GET, "/users/by-email").hasAuthority(RoleType.ADMIN.name())
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
