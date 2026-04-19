package com.jpmc.midascore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public UserRecord() {}

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
}
