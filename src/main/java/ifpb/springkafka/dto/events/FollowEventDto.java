package ifpb.springkafka.dto.events;

import java.time.Instant;
import java.util.UUID;

public record FollowEventDto(
        String eventId,
        String action,
        String followerEmail,
        String followingEmail,
        Instant timestamp
) {
    public static FollowEventDto create(String action, String follower, String following) {
        return new FollowEventDto(
                UUID.randomUUID().toString(),
                action,
                follower,
                following,
                Instant.now()
        );
    }
}