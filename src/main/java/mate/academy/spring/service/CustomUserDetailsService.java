package mate.academy.spring.service;

import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findByEmail(userName);
        } catch (DataProcessingException e) {
            throw new UsernameNotFoundException(String.format("User %s doesn't exist", userName), e);
        }
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(userName);
        builder.password(user.getPassword());
        builder.authorities(user.getRoles().stream().map(role -> role.getRoleName().name()).toArray(String[]::new));
        return builder.build();
    }
}
