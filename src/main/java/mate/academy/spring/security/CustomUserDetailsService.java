package mate.academy.spring.security;

import java.util.Optional;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
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
        Optional<User> userOptional = userService.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userOptional.orElseThrow();
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles()
                .stream()
                .map(r -> r.getRoleName().name())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
