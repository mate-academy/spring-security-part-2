package mate.academy.spring.security;

import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
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
            user = userService.findByEmail(userName).orElseThrow();
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with email: "
                    + userName + "doesn't exist in DB.", e);
        }
        UserBuilder builder = org.springframework.security.core.userdetails
                .User.withUsername(userName);
        builder.password(user.getPassword());
        builder.authorities(user.getRoles().stream().map(i -> i.getRole().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
