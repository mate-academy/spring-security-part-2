package mate.academy.spring.service.impl;

import java.util.Optional;
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
        Optional<User> optional = userService.findByEmail(username);
        UserBuilder builder;
        if (optional.isPresent()) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(optional.orElseThrow().getPassword());
            builder.authorities(optional.orElseThrow().getRoles().stream()
                    .map(role -> role.getRoleName().name())
                    .toArray(String[]::new));
            return builder.build();
        }
        throw new UsernameNotFoundException("User with this username: "
                + username + " is not found");
    }
}
