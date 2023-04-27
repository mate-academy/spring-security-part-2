package mate.academy.spring.service.impl;

import java.util.Optional;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(userOptional.get().getPassword());
        builder.authorities(userOptional.get().getRoles().stream()
                .map(Role::getRole).toArray(String[]::new));
        return builder.build();
    }
}
