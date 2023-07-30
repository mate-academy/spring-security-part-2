package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
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
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final UserService userService;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder encoder,
                          RoleService roleService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleNames.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleNames.USER);
        roleService.add(userRole);
        User user = new User();
        user.setEmail("admin@i.ua");
        user.setPassword("admin123");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
        System.out.println("First admin - admin@i.ua with password -admin123 has been injected.");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/cinema-halls", "/movies",
                        "/movie-sessions/available").hasAnyAuthority(Role.RoleNames
                        .ADMIN.name(), Role.RoleNames.USER.name())
                .antMatchers(HttpMethod.GET, "/users/by-email")
                .hasAnyAuthority(Role.RoleNames.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user", "/orders")
                .hasRole(Role.RoleNames.USER.name())
                .antMatchers(HttpMethod.DELETE, "/movie-sessions/{id}")
                .hasAnyAuthority(Role.RoleNames.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/movie-sessions/{id}")
                .hasAnyAuthority(Role.RoleNames.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/cinema-halls", "/movies",
                        "/movie-sessions").hasAnyAuthority(Role.RoleNames.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/shopping-carts/movie-sessions")
                .hasAnyAuthority(Role.RoleNames.USER.name())
                .antMatchers(HttpMethod.POST, "/orders/complete")
                .hasAnyAuthority(Role.RoleNames.USER.name())
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
