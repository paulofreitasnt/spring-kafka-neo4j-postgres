package ifpb.springkafka.controller;

import ifpb.springkafka.dto.UserCreateDto;
import ifpb.springkafka.dto.UserDto;
import ifpb.springkafka.service.UserService;
import ifpb.springkafka.service.FollowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserCreateDto userDto) {
        return userService.create(userDto)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDto> getByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/following/emails")
    public List<String> getFollowingEmails(@PathVariable Long userId) {
        return followService.getFollowingEmails(userId);
    }

    @GetMapping("/{userId}/followers/emails")
    public List<String> getFollowersEmails(@PathVariable Long userId) {
        return followService.getFollowersEmails(userId);
    }
}