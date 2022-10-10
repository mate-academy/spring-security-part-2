package mate.academy.spring.service.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.spring.dto.request.UserRequestDto;
import mate.academy.spring.dto.response.UserResponseDto;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User>,
                                    RequestDtoMapper<UserRequestDto, User> {
    private final RoleService roleService;

    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setRolesId(parseRoles(user.getRoles()));
        return responseDto;
    }

    @Override
    public User mapToModel(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        Set<Role> setRoles = dto.getRoleNames().stream()
                .distinct()
                .map(roleName -> roleService.getByName(roleName).get())
                .collect(Collectors.toSet());
        user.setRoles(setRoles);
        return user;
    }

    private List<Long> parseRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
    }
}
