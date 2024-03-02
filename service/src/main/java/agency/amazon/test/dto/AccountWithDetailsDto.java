package agency.amazon.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AccountWithDetailsDto(
        @NotNull @NotEmpty @Email String email,
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String surname,
        @NotNull @NotEmpty String password) {
}
