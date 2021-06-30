package mate.academy.spring.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role admin = new Role();
        admin.setRoleName(RoleName.ROLE_ADMIN);
        roleService.add(admin);
        Role user = new Role();
        user.setRoleName(RoleName.ROLE_USER);
        roleService.add(user);
        User danylo = new User();
        danylo.setEmail("danylo@gmail.com");
        danylo.setPassword("1234");
        danylo.setRoles(Set.of(roleService.getByName("role_admin")));
        userService.add(danylo);
    }
}
