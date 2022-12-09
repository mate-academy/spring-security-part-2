package mate.academy.spring.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String email;
    private Set<Long> roleId;
}
