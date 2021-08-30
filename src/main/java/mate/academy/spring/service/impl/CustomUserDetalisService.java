package mate.academy.spring.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetalisService implements UserDetailsService {
    private final UserService userService;
    
    public CustomUserDetalisService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userService.findByEmail(username).isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = userService.findByEmail(username).get();
        return withUsername(username)
                .password(user
                        .getPassword())
                .roles(user
                        .getRoles()
                        .stream()
                        .map(Role::getRoleName)
                        .toArray(String[]::new))
                .build();
    }
}
