package mate.academy.spring.model;

import java.util.Arrays;

public enum RoleName {
    ADMIN("ADMIN"),
    USER("USER");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RoleName getRoleByName(String name) {
        return Arrays.stream(RoleName.values())
                .filter(e -> e.getName().equals(name))
                .findFirst().get();
    }

    @Override
    public String toString() {
        return "RoleName{" +
                "name='" + name + '\'' +
                '}';
    }
}
