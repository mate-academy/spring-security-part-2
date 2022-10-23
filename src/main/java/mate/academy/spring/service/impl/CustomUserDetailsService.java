package mate.academy.spring.service.impl;

import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found by email=" + email);
        }
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(email);
        userBuilder.password(user.get().getPassword());
        userBuilder.roles(user.get().getRoles().stream()
                .map(r -> r.getRoleName().name())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
