package mate.academy.spring.dto.response;

import java.util.List;
import mate.academy.spring.model.RoleName;

public class UserResponseDto {
    private Long id;
    private String email;
    private List<RoleName> roles;

    public List<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleName> roles) {
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
