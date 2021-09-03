package mate.academy.spring.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private List<String> roles;
}
