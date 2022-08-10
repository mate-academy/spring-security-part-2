package mate.academy.spring;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public DataInitializer(RoleService roleService, UserService userService) {
        this. roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setName("USER");
        roleService.save(userRole);

        User admin = new User();
        admin.setEmail("special@gmail.com");
        admin.setPassword("123456789");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);

        User user = new User();
        user.setEmail("veryImportantUser@gmail.com");
        user.setPassword("987654321");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
