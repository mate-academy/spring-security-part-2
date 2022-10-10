package mate.academy.spring.model;

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
}

