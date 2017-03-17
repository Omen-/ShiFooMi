package com.m2dl.shifoomi.actions;

import com.m2dl.shifoomi.database.game.GameMove;
import com.m2dl.shifoomi.database.game.GameMoveType;
import com.m2dl.shifoomi.repository.game.GameRepositoryFirebase;

import org.joda.time.Instant;

public class PlayTurn {

    private final String gameId;
    private final String userId;
    private final GameMoveType gameMoveType;
    private final int turn;

    public PlayTurn(String gameId, String userId, GameMoveType gameMoveType, int turn) {
        this.gameId = gameId;
        this.userId = userId;
        this.gameMoveType = gameMoveType;
        this.turn = turn;
    }

    public void execute() {
        GameRepositoryFirebase.getInstance()
                .addGameMove(gameId, new GameMove(userId, gameMoveType, turn, Instant.now()));
    }
}
