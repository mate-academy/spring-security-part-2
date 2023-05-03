package mate.academy.spring.service.impl;

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
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> byEmail = userService.findByEmail(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (byEmail.isPresent()) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(byEmail.get().getPassword());
            userBuilder.roles(byEmail.get().getRoleSet().stream()
                    .map(e -> e.getRoleName().name())
                    .toArray(String[]::new));
            return userBuilder.build();
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
