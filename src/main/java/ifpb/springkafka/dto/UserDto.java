package ifpb.springkafka.dto;

public record UserDto(
        Long id,
        String name,
        String email) {
}
