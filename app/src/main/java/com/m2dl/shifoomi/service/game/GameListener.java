package com.m2dl.shifoomi.service.game;

import com.m2dl.shifoomi.database.game.GameMove;
import com.m2dl.shifoomi.database.game.GameMoveType;

public interface GameListener {

    void opponentPlayed(GameMoveType gameMoveType);

    void scoreUpdated();

    void roundStart();
}
