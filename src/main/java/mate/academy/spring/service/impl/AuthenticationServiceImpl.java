package mate.academy.spring.service.impl;

import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import mate.academy.spring.util.RoleType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String ADMIN_REG_KEY = "I_KNOW_ADMIN";
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleService.getRoleByName(RoleType.USER));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    @Override
    public User registerAdmin(String email, String password, String adminKey) {
        if (!adminKey.equals(ADMIN_REG_KEY)) {
            throw new BadCredentialsException("Cannot register admin user with provided admin key");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleService.getRoleByName(RoleType.ADMIN));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
