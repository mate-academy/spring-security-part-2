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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);
        User user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("Can't found user with email "
                + email));
        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(user.getPassword());
        builder.roles(user.getRoles().stream()
                .map(role -> role.getName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
