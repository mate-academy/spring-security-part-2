package mate.academy.spring.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleRequestDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    public String getName() {
        return name;
    }
}
