package mate.academy.spring.dto.request;

import lombok.Getter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class MovieRequestDto {
    @NotNull
    private String title;
    @Size(max = 200)
    private String description;
}
