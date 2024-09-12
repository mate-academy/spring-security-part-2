package mate.academy.spring.service.impl;

import static org.springframework.security.core.userdetails.User.UserBuilder;
import static org.springframework.security.core.userdetails.User.withUsername;

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
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user by email: "
                        + username));
        UserBuilder userBuilder = withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles().stream()
                .map(String::valueOf)
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
