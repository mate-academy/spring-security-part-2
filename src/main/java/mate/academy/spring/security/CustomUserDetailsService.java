package mate.academy.spring.security;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(username);
        UserBuilder builder = withUsername(username);
        builder.password(user.orElseThrow(
                () -> new UsernameNotFoundException("Couldn't find user with username: "
                        + username))
                .getPassword());
        builder.authorities(user.get().getPassword());
        builder.authorities(user.orElseThrow(
                () -> new UsernameNotFoundException("Couldn't find user with username: "
                + username))
                .getRoles()
                .stream().map(Role::getRoleName).toArray(String[]::new));
        return builder.build();
    }
}
