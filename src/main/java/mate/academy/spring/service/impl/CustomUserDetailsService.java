package mate.academy.spring.service.impl;

import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        mate.academy.spring.model.User user = userService.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("Can`t find user with email: " + email));
        User.UserBuilder builder = User.withUsername(email);
        builder.password(user.getPassword());
        builder.roles(user.getRoles()
                .stream()
                .map(r -> r.getName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
