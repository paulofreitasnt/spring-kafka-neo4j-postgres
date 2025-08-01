package ifpb.springkafka.controller;

import ifpb.springkafka.dto.UserCreateDto;
import ifpb.springkafka.dto.UserDto;
import ifpb.springkafka.model.Follow;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.FollowRepository;
import ifpb.springkafka.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowRepository followRepository;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserCreateDto userDto) {
        if (userRepository.findByEmail(userDto.email()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = new User();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        User newUser = userRepository.save(user);

        UserDto responseDto = new UserDto(newUser.getId(), newUser.getName(), newUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDto> get(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{userId}/following/emails")
    public List<String> getFollowingEmails(@PathVariable Long userId) {
        List<Follow> following = followRepository.findByFollower_Id(userId);
        return following.stream()
                .map(f -> f.getFollowing().getEmail())
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}/followers/emails")
    public List<String> getFollowersEmails(@PathVariable Long userId) {
        List<Follow> followers = followRepository.findByFollowing_Id(userId);
        return followers.stream()
                .map(f -> f.getFollower().getEmail())
                .collect(Collectors.toList());
    }

}