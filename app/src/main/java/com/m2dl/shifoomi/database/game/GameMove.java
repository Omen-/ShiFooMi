package com.m2dl.shifoomi.database.game;

import com.google.firebase.database.IgnoreExtraProperties;

import org.joda.time.Instant;

@IgnoreExtraProperties
public class GameMove {

    private String userId;
    private GameMoveType gameMoveType;
    private int turn;
    private Long timeStamp;

    public GameMove() {
    }

    public GameMove(String userId, GameMoveType gameMoveType, int turn, Instant timeStamp) {
        this.userId = userId;
        this.gameMoveType = gameMoveType;
        this.timeStamp = timeStamp.getMillis();
        this.turn = turn;
    }

    public String getUserId() {
        return userId;
    }

    public GameMoveType getGameMoveType() {
        return gameMoveType;
    }

    public int getTurn() {
        return turn;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
