package ifpb.springkafka.controller;

import ifpb.springkafka.model.Friendship;
import ifpb.springkafka.model.FriendshipRequest;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.FriendshipRepository;
import ifpb.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Friendship create(@RequestBody FriendshipRequest request){
        User user1 = userRepository.findByEmail(request.getEmail1());
        User user2 = userRepository.findByEmail(request.getEmail2());
        if(user1 == null || user2 == null){
            throw new RuntimeException("User not found");
        }
        Friendship friendship = new Friendship();
        friendship.setUser1(user1);
        friendship.setUser2(user2);
        friendship.setCreatedAt(LocalDateTime.now());
        return friendshipRepository.save(friendship);
    }

    @GetMapping
    public List<Friendship> getAll(){
        return friendshipRepository.findAll();
    }

}