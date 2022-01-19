package mate.academy.spring.service;

import lombok.AllArgsConstructor;
import mate.academy.spring.model.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByEmail(userName).orElseThrow(() ->
                new RuntimeException("Can`t find user by Email"));
        UserBuilder builder = org.springframework.security
                .core.userdetails.User.withUsername(userName);
        builder.password(user.getPassword());
        builder.roles(user.getRoles()
                .stream()
                .map(role -> role.getRoleName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
