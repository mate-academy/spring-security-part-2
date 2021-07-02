package mate.academy.spring.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private RoleName name;

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public enum RoleName {
        USER,
        ADMIN
    }

    @Override
    public boolean equals(Object role) {
        if (this == role) {
            return true;
        }
        if (role == null || getClass() != role.getClass()) {
            return false;
        }
        Role otherRole = (Role) role;
        return id.equals(otherRole.id) && name == otherRole.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
