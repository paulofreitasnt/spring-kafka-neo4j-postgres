package ifpb.springkafka.controller;

import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/find")
    public User get(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

}
