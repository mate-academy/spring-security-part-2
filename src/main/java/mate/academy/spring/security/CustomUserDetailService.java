package mate.academy.spring.security;

import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User with username " + email + " not found")
        );
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
                org.springframework.security.core.userdetails.User.withUsername(email);

        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRoles()
                .stream()
                .map(role -> role.getName().toString())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
