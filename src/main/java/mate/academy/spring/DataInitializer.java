package mate.academy.spring;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleService.add(userRole);

        User maks = new User();
        maks.setEmail("maksym@gmail.com");
        maks.setPassword("1");
        maks.setRoles(Set.of(adminRole));
        userService.add(maks);

        User ivan = new User();
        ivan.setEmail("ivan@gmail.com");
        ivan.setPassword("1");
        ivan.setRoles(Set.of(userRole));
        userService.add(ivan);
    }
}
