package ifpb.springkafka.service;

import ifpb.springkafka.dto.UserCreateDto;
import ifpb.springkafka.dto.FollowEventDto;
import ifpb.springkafka.dto.UserDto;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${kafka.topic.follow}")
    private String followsTopic;

    @Autowired
    private KafkaTemplate<String, FollowEventDto> kafkaTemplate;

    public Optional<UserDto> create(UserCreateDto userDto) {
        if (userRepository.findByEmail(userDto.email()) != null) {
            return Optional.empty();
        }
        User user = new User();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        User newUser = userRepository.save(user);

        // Publica evento de criação de usuário
        FollowEventDto event = new FollowEventDto(
                "CREATE_USER",
                user.getEmail(),
                null
        );
        kafkaTemplate.send(followsTopic, event);

        return Optional.of(new UserDto(newUser.getId(), newUser.getName(), newUser.getEmail()));
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail()));
    }

    public Optional<UserDto> findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) return Optional.empty();
        return Optional.of(new UserDto(user.getId(), user.getName(), user.getEmail()));
    }
}