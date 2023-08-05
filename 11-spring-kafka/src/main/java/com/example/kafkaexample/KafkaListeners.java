package com.example.kafkaexample;

import com.example.kafkaexample.config.KafkaTopicConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = KafkaTopicConfig.TOPIC, groupId = "example-group-id")
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}
