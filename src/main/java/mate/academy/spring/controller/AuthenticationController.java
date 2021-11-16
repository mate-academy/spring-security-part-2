package mate.academy.spring.controller;

import java.util.Set;
import javax.validation.Valid;
import mate.academy.spring.dto.request.UserRequestDto;
import mate.academy.spring.dto.response.UserResponseDto;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    public AuthenticationController(AuthenticationService authService,
                                    UserMapper userMapper,
                                    RoleService roleService) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        Role userRole = roleService.getRoleByName("USER");
        User user = authService.register(requestDto.getEmail(), requestDto.getPassword(),
                Set.of(userRole));
        return userMapper.mapToDto(user);
    }
}
