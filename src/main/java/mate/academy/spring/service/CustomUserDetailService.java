package mate.academy.spring.service;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import static org.springframework.security.core.userdetails.User.withUsername;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserServiceImpl userService;

    @Autowired
    public CustomUserDetailService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username
                        + " not found!"));
        UserBuilder userBuilder =
                withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
