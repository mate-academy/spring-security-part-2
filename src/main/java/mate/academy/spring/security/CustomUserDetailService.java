package mate.academy.spring.security;

import java.util.Optional;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByEmail(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            org.springframework.security.core.userdetails.User.UserBuilder builder =
                    org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.username(username);
            builder.roles(user.getRoles().stream()
                    .map(role -> role.getName().name())
                    .toArray(String[]::new));
            return builder.build();
        }
        throw new UsernameNotFoundException("User with username:" + username + " not found");
    }
}
