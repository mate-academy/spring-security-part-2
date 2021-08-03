package mate.academy.spring.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.User;
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
        Optional<mate.academy.spring.model.User> userOptional = userService.findByEmail(username);
        mate.academy.spring.model.User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("Cant find user with email: " + username));
        return User
                .withUsername(username)
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(role -> role.getRoleName().name())
                        .toArray(String[]::new))
                .build();
    }
}
