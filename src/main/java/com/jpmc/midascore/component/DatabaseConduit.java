package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;

@Component
public class DatabaseConduit {

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "transactions", groupId = "midas-group")
    public void listen(String message) {

        // Example message format: "Alice,100.0"
        String[] parts = message.split(",");

        String name = parts[0];
        Float amount = Float.parseFloat(parts[1]);

        UserRecord user = new UserRecord(name, amount);

        userRepository.save(user);
    }
}
