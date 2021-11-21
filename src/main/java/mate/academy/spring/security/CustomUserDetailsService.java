package mate.academy.spring.security;

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
    UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.orElseThrow(
                () -> new UsernameNotFoundException("Can't find user with email: " + email)
        );
        org.springframework.security.core.userdetails.User.UserBuilder builder
                = org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(user.getPassword());
        builder.authorities(
                user
                        .getRoles()
                        .stream()
                        .map(Role::getName)
                        .toArray(String[]::new)
        );
        return builder.build();
    }
}
