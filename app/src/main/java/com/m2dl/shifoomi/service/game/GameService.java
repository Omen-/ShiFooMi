package com.m2dl.shifoomi.service.game;

import com.m2dl.shifoomi.database.game.Game;
import com.m2dl.shifoomi.database.game.GameMove;
import com.m2dl.shifoomi.database.game.GameMoveType;
import com.m2dl.shifoomi.repository.game.GameRepositoryFirebase;
import com.m2dl.shifoomi.repository.game.GameRepositoryListener;

public class GameService {

    private static final GameService instance = new GameService();

    public static GameService getInstance() {
        return instance;
    }

    public void addListener(final String userId, final GameListener gameListener) {
        GameRepositoryFirebase.getInstance().addGameRepositoryListener(userId, new GameRepositoryListener() {
            @Override
            public void gameUpdate(Game game) {
                if(game.getGameMoves().isEmpty())
                    return;
                gameListener.scoreUpdated(computeScore(userId, game));
                GameMove newMove = game.getGameMoves().get(game.getGameMoves().size() - 1);
                if(!newMove.getUserId().equals(userId))
                    gameListener.opponentPlayed(newMove.getGameMoveType());
                if(isTurnFinished(game))
                    gameListener.roundStart();
            }
        });
    }

    private boolean isTurnFinished(Game game) {
        return game.getGameMoves().size() % 2 == 0;
    }

    private GameScore computeScore(String playerId, Game game) {
        GameMove playerMove = null;
        GameMove opponentMove = null;
        int playerScore = 0;
        int opponentScore = 0;
        int turn = 0;
        do {

            if (playerMove != null) {
                if (firstPlayerWin(playerMove.getGameMoveType(), opponentMove.getGameMoveType()))
                    playerScore++;
                else
                    opponentScore++;
            }

            playerMove = null;
            opponentMove = null;

            for (GameMove gameMove : game.getGameMoves()) {
                if (gameMove.getTurn() == turn) {
                    if (gameMove.getUserId().equals(playerId))
                        playerMove = gameMove;
                    else
                        opponentMove = gameMove;
                }
            }

            turn++;
        } while (playerMove != null && opponentMove != null);
        return new GameScore(opponentScore, playerScore);
    }

    private boolean firstPlayerWin(GameMoveType firstPlayer, GameMoveType secondPlayer) {
        return ((secondPlayer == GameMoveType.LOOSE && firstPlayer != GameMoveType.LOOSE) ||
                ((firstPlayer == GameMoveType.SCISSORS && secondPlayer == GameMoveType.PAPER) ||
                        (firstPlayer == GameMoveType.PAPER && secondPlayer == GameMoveType.ROCK) ||
                        (firstPlayer == GameMoveType.ROCK && secondPlayer == GameMoveType.SCISSORS)));
    }
}
