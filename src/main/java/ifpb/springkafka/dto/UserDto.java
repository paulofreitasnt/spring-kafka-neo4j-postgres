package ifpb.springkafka.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank (message="Name is required") String name,
        @NotBlank (message="Email is required") @Email (message = "Invalid Email") String email) {
}
