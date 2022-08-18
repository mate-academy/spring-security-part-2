package mate.academy.spring.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.spring.dto.response.RoleResponseDto;
import mate.academy.spring.dto.response.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        Set<RoleResponseDto> roles = user.getRoles().stream()
                .map(role -> new RoleResponseDto(role.getId(), role.getRoleName()))
                .collect(Collectors.toSet());
        responseDto.setRoles(roles);
        return responseDto;
    }
}
