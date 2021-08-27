package mate.academy.spring.service;

import static org.springframework.security.core.userdetails.User.withUsername;

import mate.academy.spring.model.User;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userService.findByEmail(email).isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + email + " not found!");
        }
        User user = userService.findByEmail(email).get();
        return withUsername(email)
                .password(user.getPassword())
                .roles(user.getRoles()
                        .stream()
                        .map(s -> s.getName().name())
                        .toArray(String[]::new)).build();
    }
}
