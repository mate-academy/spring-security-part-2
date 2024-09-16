package mate.academy.spring.dto.response;

import java.util.Set;
import mate.academy.spring.model.Role;

public class UserResponseDto {
    private Long id;
    private String email;
    private Set<Role> roles;

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

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> role) {
        this.roles = role;
    }
}
