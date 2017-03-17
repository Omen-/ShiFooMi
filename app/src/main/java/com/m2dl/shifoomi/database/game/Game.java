package com.m2dl.shifoomi.database.game;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@IgnoreExtraProperties
public class Game {

    private String id;
    private String firstPlayerId;
    private String secondPlayerId;
    private Map<String, GameMove> gameMoves;

    public Game() {
    }

    public Game(String firstPlayerId, String secondPlayerId) {
        this.id = UUID.randomUUID().toString();
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        gameMoves = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getFirstPlayerId() {
        return firstPlayerId;
    }

    public String getSecondPlayerId() {
        return firstPlayerId;
    }

    public List<GameMove> getGameMoves() {
        return new ArrayList<>(gameMoves.values());
    }
}
