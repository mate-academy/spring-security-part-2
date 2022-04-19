package mate.academy.spring.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private RoleNames roleName;

    @Override
    public String getAuthority() {
        return "ROLE_" + roleName.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleNames getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleNames roleName) {
        this.roleName = roleName;
    }

    public enum RoleNames {
        USER,
        ADMIN
    }
}
