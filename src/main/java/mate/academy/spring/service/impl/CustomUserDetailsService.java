package mate.academy.spring.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Optional;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return withUsername(email)
                .password(user.get().getPassword())
                .authorities(user.get().getRoles().stream()
                        .map(Role::getName)
                        .toArray(String[]::new)).build();
    }
}
