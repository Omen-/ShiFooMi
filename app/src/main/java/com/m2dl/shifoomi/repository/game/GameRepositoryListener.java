package com.m2dl.shifoomi.repository.game;

import com.m2dl.shifoomi.database.game.Game;

public interface GameRepositoryListener {

    void gameCreated(Game game);
}
