package com.m2dl.shifoomi.database.room;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.UUID;

@IgnoreExtraProperties
public class Room {

    private String id;
    private String userId;
    private String name;

    public Room() {
    }

    public Room(String userId, String name) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
