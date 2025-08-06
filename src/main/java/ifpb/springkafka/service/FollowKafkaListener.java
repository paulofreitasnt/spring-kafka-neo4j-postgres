package ifpb.springkafka.service;

import ifpb.springkafka.dto.events.FollowEventDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FollowKafkaListener {

    @KafkaListener(topics="${kafka.topic.follow}", groupId = "follow-listener-group",
    containerFactory = "kafkaListenerContainerFactory")
    public void listen(FollowEventDto event){
        System.out.println(event);
    }

}
