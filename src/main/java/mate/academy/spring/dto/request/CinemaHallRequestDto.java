package mate.academy.spring.dto.request;

import lombok.Getter;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
public class CinemaHallRequestDto {
    @Min(10)
    private int capacity;
    @Size(max = 200)
    private String description;
}
