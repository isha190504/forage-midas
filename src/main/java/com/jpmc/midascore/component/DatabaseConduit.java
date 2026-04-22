package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.UserRepository;

@Component
public class DatabaseConduit {

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "transactions", groupId = "midas-group")
    public void process(Transaction transaction) {

        UserRecord sender = userRepository.findByName(transaction.getFrom());
        UserRecord receiver = userRepository.findByName(transaction.getTo());

        if (sender != null && receiver != null) {

            Float amount = transaction.getAmount();

            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            userRepository.save(sender);
            userRepository.save(receiver);
        }
    }
}
