package ifpb.springkafka.dto;

public record FollowEventDto(
        String action,
        String followerEmail,
        String followingEmail
) {}
