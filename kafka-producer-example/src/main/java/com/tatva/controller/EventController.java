package com.tatva.controller;

import com.tatva.dto.User;
import com.tatva.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@RequestBody User message) {
        try {
            for (int i = 0; i <= 100000; i++) {
                publisher.sendMessageToTopic(message);
            }
            return ResponseEntity.ok("message published successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/publish")
    public void sendEvents(@RequestBody User user) {
        publisher.sendEventsToTopic(user);
    }


}
