package mate.academy.spring.model;

import java.util.Arrays;

public enum RoleName {
    ADMIN("ADMIN"),
    USER("USER");

    private final String stringName;

    RoleName(String stringName) {
        this.stringName = stringName;
    }

    public String getStringName() {
        return stringName;
    }

    public static RoleName getRoleNameByString(String roleName) {
        return Arrays.stream(RoleName.values())
                .filter(rn -> rn.getStringName().equals(roleName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role name: "
                        + roleName + " not find!"));
    }
}
