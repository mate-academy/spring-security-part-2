package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.response.RoleResponseDto;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements ResponseDtoMapper<RoleResponseDto, Role> {
    private final RoleService roleService;

    public RoleMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleResponseDto mapToDto(Role role) {
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
}
