package mate.academy.spring.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Authority role;

    public enum Authority {
        ADMIN("ADMIN"),
        USER("USER");

        private final String name;

        Authority(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Authority getEnumByName(String name) {
            for (Authority authority : Authority.values()) {
                if (authority.getName().equalsIgnoreCase(name)) {
                    return authority;
                }
            }
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authority getRole() {
        return role;
    }

    public void setRole(Authority role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) && this.role == role.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
