package mate.academy.spring.model;

public enum RoleName {
    ADMIN("ADMIN"),
    USER("USER");
    private String val;

    RoleName(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
