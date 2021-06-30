package mate.academy.spring.security;

import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(email);
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles().stream()
                .map(r -> r.getRoleType().name())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
