package ifpb.springkafka.controller;

import ifpb.springkafka.dto.UserDto;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.UserRepository;
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
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        if (userRepository.findByEmail(userDto.email()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = new User();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        User newUser = userRepository.save(user);

        UserDto responseDto = new UserDto(newUser.getName(), newUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto(user.getName(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDto> get(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = new UserDto(user.getName(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }

}
