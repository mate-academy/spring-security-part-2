package mate.academy.spring.service.impl;

import java.util.Optional;

import mate.academy.spring.model.Role;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(userName);
        UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(userName);
        userBuilder.password(user.get().getPassword());
        userBuilder.authorities(user.get().getRoles().stream()
                .map(Role::getRole)
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
