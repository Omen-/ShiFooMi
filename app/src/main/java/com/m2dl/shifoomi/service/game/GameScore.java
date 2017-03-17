package com.m2dl.shifoomi.service.game;

public class GameScore {

    private final int opponentScore;
    private final int playerScore;

    public GameScore(int opponentScore, int playerScore) {
        this.opponentScore = opponentScore;
        this.playerScore = playerScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
