package ifpb.springkafka.repository;

import ifpb.springkafka.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
