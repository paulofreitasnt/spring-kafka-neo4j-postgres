package ifpb.springkafka.controller;

import ifpb.springkafka.dto.UserDto;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        User user = new User();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        userRepository.save(user);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/find")
    public ResponseEntity<UserDto> get(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto(user.getName(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }

}
