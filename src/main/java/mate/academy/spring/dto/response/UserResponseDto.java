package mate.academy.spring.dto.response;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import mate.academy.spring.model.Role;

@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private Set<Role> roles;
}
