package mate.academy.spring.security;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found. Email: " + username));
        return withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                    .map(r -> r.getRoleName().toString())
                    .toArray(String[]::new))
                .build();
    }
}
