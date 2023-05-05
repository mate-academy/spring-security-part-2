package mate.academy.spring.security;

import java.util.Optional;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        Optional<User> optionalUser = userService.findByEmail(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder
                = withUsername(username);
        builder.password(user.getPassword());
        builder.authorities(user.getRoles().stream()
                .map(r -> r.getRoleName()
                        .name())
                .toArray(String[]::new));
        return builder.build();
    }
}
