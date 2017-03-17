package com.m2dl.shifoomi.database.user;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String id;
    private String name;
    private float eloScore;

    public User() {
    }

    public User(String name, float eloScore) {
        this.name = name;
        this.eloScore = eloScore;
    }

    public String getName() {
        return name;
    }

    public float getEloScore() {
        return eloScore;
    }
}
