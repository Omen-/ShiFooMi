package com.m2dl.shifoomi.actions;

import com.m2dl.shifoomi.database.game.Game;
import com.m2dl.shifoomi.database.room.Room;
import com.m2dl.shifoomi.repository.game.GameRepositoryFirebase;
import com.m2dl.shifoomi.repository.room.RoomRepositoryFirebase;

public class CreateGameFromRoom {

    private final Room room;
    private final String userId;

    public CreateGameFromRoom(Room room, String userId) {
        this.room = room;
        this.userId = userId;
    }

    public void execute() {
        RoomRepositoryFirebase.getInstance().deleteRoom(room.getId());
        Game game = new Game(room.getUserId(), userId);
        GameRepositoryFirebase.getInstance().createGame(game);
    }
}
