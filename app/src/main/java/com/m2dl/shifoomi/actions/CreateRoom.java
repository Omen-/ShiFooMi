package com.m2dl.shifoomi.actions;

import com.m2dl.shifoomi.database.room.Room;
import com.m2dl.shifoomi.repository.room.RoomRepository;
import com.m2dl.shifoomi.repository.room.RoomRepositoryFirebase;

public class CreateRoom {

    private final String name;
    private final String userId;

    public CreateRoom(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public void execute() {
        RoomRepositoryFirebase.getInstance().createRoom(new Room(userId, name));
    }
}
