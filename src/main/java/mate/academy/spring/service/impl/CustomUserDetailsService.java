package mate.academy.spring.service.impl;

import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(userFromDb.getPassword());
        builder.roles(userFromDb.getRoles().stream()
                .map(role -> role.getRoleName().toString()).toArray(String[]::new));
        return builder.build();
    }
}
