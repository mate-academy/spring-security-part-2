package mate.academy.spring.dto.response;

import mate.academy.spring.model.Role;

public class RoleResponseDto {
    private final Long id;
    private final Role.RoleName roleName;

    public RoleResponseDto(Long id, Role.RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public Role.RoleName getRoleName() {
        return roleName;
    }
}
