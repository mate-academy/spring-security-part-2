package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    public void inject() {
        Role adminRole = Role.builder().roleType(Role.RoleName.ADMIN).build();
        roleService.add(adminRole);

        Role userRole = Role.builder().roleType(Role.RoleName.USER).build();
        roleService.add(userRole);

        userService.add(User.builder().email("admin@gmail.com")
                .password("Admin!1234").roles(Set.of(adminRole)).build());

        userService.add(User.builder().email("bob@gmail.com")
                .password("Bob!1234").roles(Set.of(userRole)).build());
    }
}
