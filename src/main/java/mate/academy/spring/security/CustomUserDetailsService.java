package mate.academy.spring.security;

import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findByEmail(s);
        } catch (DataProcessingException e) {
            throw new UsernameNotFoundException("Can`t find user with with email:" + s, e);
        }
        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(s);
        builder.password(user.getPassword());
        builder.authorities(user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
