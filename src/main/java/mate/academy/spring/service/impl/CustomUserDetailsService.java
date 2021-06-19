package mate.academy.spring.service.impl;

import mate.academy.spring.model.Role;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        mate.academy.spring.model.User user = userService.findByEmail(username);
        UserBuilder builder = User.withUsername(username);
        builder.password(user.getPassword());
        builder.authorities(user.getRoles()
                .stream()
                .map(Role::getName)
                .toArray(String[]::new));
        return builder.build();
    }
}
