package mate.academy.spring.service;

import java.util.Arrays;
import java.util.Optional;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);
        UserBuilder builder;
        if (userOptional.isPresent()) {
            builder = withUsername(email);
            builder.password(userOptional.get().getPassword());
            builder.roles(Arrays.toString(userOptional.get().getRoles().stream()
                    .map(Role::getName)
                    .toArray(Role.RoleName[]::new)));
            return builder.build();
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
