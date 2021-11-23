package mate.academy.spring.model;

import java.util.Arrays;

public enum RoleName {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    RoleName(String role) {
        this.role = role;
    }

    public static RoleName getValue(String roleType) {
        return Arrays.stream(RoleName.values())
                .filter(roleName -> roleName.role.equals(roleType))
                .findFirst().orElseThrow();
    }
}
