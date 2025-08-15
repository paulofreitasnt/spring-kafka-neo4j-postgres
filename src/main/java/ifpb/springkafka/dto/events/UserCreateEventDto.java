package ifpb.springkafka.dto.events;

import ifpb.springkafka.model.User;

import java.time.Instant;
import java.util.UUID;

public record UserCreateEventDto (
        String eventId,
        String action,
        Long id,
        String email,
        Instant timestamp
){
    public static UserCreateEventDto of(User user) {
        return new UserCreateEventDto(
                UUID.randomUUID().toString(),
                "CREATE_USER",
                user.getId(),
                user.getEmail(),
                Instant.now()
        );
    }
}


