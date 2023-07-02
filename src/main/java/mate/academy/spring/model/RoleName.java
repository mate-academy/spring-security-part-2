package mate.academy.spring.model;

public enum RoleName {
    ADMIN("admin"),
    USER("user");

    private String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
