package mate.academy.spring.model;

import java.util.Arrays;

public enum RoleType {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    public static RoleType getEnumValue(String role) {
        return Arrays.stream(RoleType.values())
                .filter(r -> r.role.equals(role))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can`t find role type such as "
                        + role));
    }
}
