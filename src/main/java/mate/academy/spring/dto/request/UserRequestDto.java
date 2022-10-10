package mate.academy.spring.dto.request;

import java.util.List;
import javax.validation.constraints.Size;
import mate.academy.spring.lib.FieldsValueMatch;
import mate.academy.spring.lib.ValidEmail;
import mate.academy.spring.lib.ValidRoles;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
    @ValidRoles
    private List<String> roleNames;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }
}
