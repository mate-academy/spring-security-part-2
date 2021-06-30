package mate.academy.spring.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }

    public String getName() {
        return name().replace("ROLE_", "");
    }
}
