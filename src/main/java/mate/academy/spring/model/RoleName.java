package mate.academy.spring.model;

public enum RoleName {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String toString() {
        return this.name().replace("ROLE_", "");
    }
}
