package mate.academy.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName roleName;

    public enum RoleName{
        ADMIN("ADMIN"),
        USER("USER");

        private final String role;

        RoleName(String role) {
            this.role = role;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName.role;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
