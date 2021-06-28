package mate.academy.spring.service.impl;

import java.util.Set;
import mate.academy.spring.dao.UserDao;
import mate.academy.spring.exception.DataProcessingException;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final UserDao userDao;

    public UserServiceImpl(PasswordEncoder encoder, RoleService roleService, UserDao userDao) {
        this.encoder = encoder;
        this.roleService = roleService;
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id).orElseThrow(
                () -> new DataProcessingException("User with id " + id + " not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElseThrow(
                () -> new DataProcessingException("User with email " + email + " not found"));
    }
}
