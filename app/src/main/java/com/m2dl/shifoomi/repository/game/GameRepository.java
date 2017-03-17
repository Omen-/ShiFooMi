package com.m2dl.shifoomi.repository.game;

import com.m2dl.shifoomi.database.game.Game;
import com.m2dl.shifoomi.database.game.GameMove;

public interface GameRepository {

    void addGameMove(String gameId, GameMove gameMove);

    void createGame(Game game);

    void addGameRepositoryListener(String userId, GameRepositoryListener gameRepositoryListener);

    void removeGameRepositoryListener(String userId, GameRepositoryListener gameRepositoryListener);
}
