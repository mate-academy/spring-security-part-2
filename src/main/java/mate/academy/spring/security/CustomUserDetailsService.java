package mate.academy.spring.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserBuilder builder = withUsername(username);
        builder.password(user.getPassword());
        builder.username(user.getEmail());
        builder.authorities(user.getRoles().stream()
                .map(Role::getName)
                .map(Role.RoleName::name)
                .toArray(String[]::new));
        return builder.build();
    }
}
