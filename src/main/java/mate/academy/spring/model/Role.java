package mate.academy.spring.model;

import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Authority role;

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
            return Arrays.stream(Authority.values())
                    .filter(e -> e.getName()
                            .equals(name))
                    .findFirst().get();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) && role == role1.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
