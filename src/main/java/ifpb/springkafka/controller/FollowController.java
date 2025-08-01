package ifpb.springkafka.controller;

import ifpb.springkafka.model.Follow;
import ifpb.springkafka.model.FollowRequest;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.FollowRepository;
import ifpb.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Follow create(@RequestBody FollowRequest request){
        User follower = userRepository.findByEmail(request.getEmailFollower());
        User following = userRepository.findByEmail(request.getEmailFollowing());
        if(follower == null || following == null){
            throw new RuntimeException("User not found");
        }
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follow.setCreatedAt(LocalDateTime.now());
        return followRepository.save(follow);
    }

    @GetMapping
    public List<Follow> getAll(){
        return followRepository.findAll();
    }

}