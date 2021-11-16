package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    public DataInitializer(RoleService roleService, AuthenticationService authenticationService) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    public void inject() {
        Role user = new Role();
        user.setName(RoleName.USER);
        Role admin = new Role();
        admin.setName(RoleName.ADMIN);
        roleService.add(user);
        roleService.add(admin);

        authenticationService.register("vlad@gmail.com", "1234", Set.of(admin));
        authenticationService.register("user@gmail.com", "1234", Set.of(user));
    }
}
