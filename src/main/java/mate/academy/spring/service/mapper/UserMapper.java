package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.response.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setRoles(user.getRoles()
                .stream()
                .map(e -> e.getRole().getName())
                .collect(Collectors.toSet()));
        return responseDto;
    }
}
