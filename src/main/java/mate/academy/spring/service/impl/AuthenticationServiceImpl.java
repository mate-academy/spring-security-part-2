package mate.academy.spring.service.impl;

import java.util.HashSet;
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
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    @Override
    public User register(String email, String password) {
        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(roleService.getRoleByName(Role.RoleType.USER.name()));
        User user = User.builder().email(email).password(password).roles(rolesSet).build();
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
