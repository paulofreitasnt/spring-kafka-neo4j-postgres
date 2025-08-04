package ifpb.springkafka.controller;

import ifpb.springkafka.dto.FollowCreateDto;
import ifpb.springkafka.model.Follow;
import ifpb.springkafka.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping
    public ResponseEntity<FollowCreateDto> create(@RequestBody FollowCreateDto request) {
        Follow follow = followService.create(request);
        FollowCreateDto resultDto =
                new FollowCreateDto(follow.getFollower().getEmail(),
                        follow.getFollowing().getEmail());
        return ResponseEntity.ok(resultDto);
    }
}