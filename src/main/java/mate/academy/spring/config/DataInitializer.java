package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.dao.RoleDao;
import mate.academy.spring.dao.impl.RoleDaoImpl;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    protected final SessionFactory factory;
    private final RoleDao roleDao;
    private final UserService userService;

    public DataInitializer(RoleDao roleDao, UserService userService, SessionFactory factory) {
        this.roleDao = roleDao;
        this.userService = userService;
        this.factory = factory;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleDao.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleDao.add(userRole);
        User user = new User();
        user.setEmail("admin@i.ua");
        user.setPassword("admin123");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
        RoleDao roleDao1 = new RoleDaoImpl(factory);
    }
}
