package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.UserRepository;

@Component
public class DatabaseConduit {

    @Autowired
    private UserRepository userRepository;

    public void process(Transaction transaction) {

        // Find sender
        UserRecord sender = userRepository.findByName(transaction.getSender());

        // Find receiver
        UserRecord receiver = userRepository.findByName(transaction.getRecipient());

        if (sender != null && receiver != null) {

            Float amount = transaction.getAmount();

            // Deduct from sender
            sender.setBalance(sender.getBalance() - amount);

            // Add to receiver
            receiver.setBalance(receiver.getBalance() + amount);

            userRepository.save(sender);
            userRepository.save(receiver);
        }
    }
}
