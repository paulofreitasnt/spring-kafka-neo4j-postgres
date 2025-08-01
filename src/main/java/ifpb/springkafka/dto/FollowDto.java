package ifpb.springkafka.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FollowDto(
        @NotBlank (message= "Follower email is required") @Email(message = "Invalid Email") String followerEmail,
        @NotBlank (message = "Following email is required") @Email(message = "Invalid Email") String followingEmail
) {
}
