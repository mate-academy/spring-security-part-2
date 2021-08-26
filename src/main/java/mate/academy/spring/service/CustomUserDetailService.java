package mate.academy.spring.service;

import static org.springframework.security.core.userdetails.User.UserBuilder;
import static org.springframework.security.core.userdetails.User.withUsername;

import mate.academy.spring.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User userByEmail = userService.findByEmail(email);
        UserBuilder userBuilder = withUsername(email);
        userBuilder.password(userByEmail.getPassword());
        userBuilder.roles(userByEmail.getRoles().stream()
                .map(role -> role.getName().toString()).toArray(String[]::new));
        return userBuilder.build();
    }
}
