package ifpb.springkafka.controller;

import ifpb.springkafka.dto.FollowCreateDto;
import ifpb.springkafka.model.Follow;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.FollowRepository;
import ifpb.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FollowCreateDto> create(@RequestBody FollowCreateDto request){
        User follower = userRepository.findByEmail(request.followerEmail());
        User following = userRepository.findByEmail(request.followingEmail());
        if(follower == null || following == null){
            throw new RuntimeException("User not found");
        }
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follow.setCreatedAt(LocalDateTime.now());
        Follow newFollow = followRepository.save(follow);
        FollowCreateDto resultDto =
                new FollowCreateDto(newFollow.getFollower().getEmail(),
                        newFollow.getFollowing().getEmail());
        return ResponseEntity.ok(resultDto);
    }

}