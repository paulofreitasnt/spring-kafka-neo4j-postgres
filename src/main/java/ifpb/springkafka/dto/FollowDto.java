package ifpb.springkafka.dto;

import jakarta.validation.constraints.NotBlank;

public record FollowDto(
        @NotBlank (message= "Follower email is required") String followerEmail,
        @NotBlank (message = "Following email is required") String followingEmail
) {
}
