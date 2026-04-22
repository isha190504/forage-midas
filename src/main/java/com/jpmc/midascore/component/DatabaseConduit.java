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

        // Find sender using ID
        UserRecord sender = userRepository.findById(transaction.getSenderId()).orElse(null);

        // Find receiver using ID
        UserRecord receiver = userRepository.findById(transaction.getRecipientId()).orElse(null);

        if (sender != null && receiver != null) {

            Float amount = transaction.getAmount();

            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);

            userRepository.save(sender);
            userRepository.save(receiver);
        }
    }
}
