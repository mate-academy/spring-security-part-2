package mate.academy.spring.service.impl;

import java.util.Set;
import lombok.AllArgsConstructor;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.getByName(Role.RoleName.USER.name())));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
