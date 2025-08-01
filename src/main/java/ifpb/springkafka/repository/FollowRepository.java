package ifpb.springkafka.repository;

import ifpb.springkafka.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollower_Id(Long followerId);
    List<Follow> findByFollowing_Id(Long followingId);

}