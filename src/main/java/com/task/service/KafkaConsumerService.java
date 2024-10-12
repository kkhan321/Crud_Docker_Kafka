package com.task.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "WELCOME_MSG", groupId = "group_id")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
