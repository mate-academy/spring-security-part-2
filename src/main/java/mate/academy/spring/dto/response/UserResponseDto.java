package mate.academy.spring.dto.response;

import java.util.Set;

public class UserResponseDto {
    private Long id;
    private String email;
    private Set<RoleResponseDto> roles;

    public Set<RoleResponseDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleResponseDto> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
