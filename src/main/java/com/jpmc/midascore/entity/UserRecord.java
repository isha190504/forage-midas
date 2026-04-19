package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Float balance;   // ✅ REQUIRED

    public UserRecord() {}

    // ✅ REQUIRED CONSTRUCTOR
    public UserRecord(String name, Float balance) {
        this.name = name;
        this.balance = balance;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public Float getBalance() { return balance; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setBalance(Float balance) { this.balance = balance; }
}
