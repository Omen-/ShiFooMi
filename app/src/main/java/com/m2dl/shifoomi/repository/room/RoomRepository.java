package com.m2dl.shifoomi.repository.room;

import com.m2dl.shifoomi.database.room.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> getRooms();

    void createRoom(Room room);

    void deleteRoom(String roomId);
}
