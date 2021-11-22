package mate.academy.spring.security;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email).get();
        UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(email);
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .map(Enum::toString)
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
