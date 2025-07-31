package ifpb.springkafka.controller;

import ifpb.springkafka.model.Friendship;
import ifpb.springkafka.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @PostMapping
    public Friendship create(@RequestBody Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    @GetMapping
    public List<Friendship> findAll() {
        return friendshipRepository.findAll();
    }

}