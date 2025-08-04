package ifpb.springkafka.service;

import ifpb.springkafka.dto.FollowCreateDto;
import ifpb.springkafka.model.Follow;
import ifpb.springkafka.model.User;
import ifpb.springkafka.repository.FollowRepository;
import ifpb.springkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;
    @Value("${kafka.topic.follow}")
    private String followTopic;
    @Autowired
    private KafkaTemplate<String, Follow> kafkaTemplate;

    @Autowired
    private UserRepository userRepository;

    public Follow create(FollowCreateDto request) {
        User follower = userRepository.findByEmail(request.followerEmail());
        User following = userRepository.findByEmail(request.followingEmail());
        if (follower == null || following == null) {
            throw new RuntimeException("User not found");
        }
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follow.setCreatedAt(LocalDateTime.now());

        kafkaTemplate.send(followTopic, follow);

        return followRepository.save(follow);
    }

    public List<String> getFollowingEmails(Long userId) {
        List<Follow> following = followRepository.findByFollower_Id(userId);
        return following.stream()
                .map(f -> f.getFollowing().getEmail())
                .collect(Collectors.toList());
    }

    public List<String> getFollowersEmails(Long userId) {
        List<Follow> followers = followRepository.findByFollowing_Id(userId);
        return followers.stream()
                .map(f -> f.getFollower().getEmail())
                .collect(Collectors.toList());
    }
}