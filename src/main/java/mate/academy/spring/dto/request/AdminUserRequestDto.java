package mate.academy.spring.dto.request;

import javax.validation.constraints.Size;
import lombok.Getter;
import mate.academy.spring.lib.FieldsValueMatch;
import mate.academy.spring.lib.ValidEmail;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Getter
public class AdminUserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
    private String adminKey;
}
