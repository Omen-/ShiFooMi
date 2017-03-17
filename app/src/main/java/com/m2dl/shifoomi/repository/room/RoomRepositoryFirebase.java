package com.m2dl.shifoomi.repository.room;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m2dl.shifoomi.database.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomRepositoryFirebase implements RoomRepository, ValueEventListener {

    private static final RoomRepository instance;

    static {
        instance = new RoomRepositoryFirebase();
    }

    public static RoomRepository getInstance() {
        return instance;
    }

    private static final String ROOM_REPOSITORY = "rooms";

    private List<Room> rooms;

    private RoomRepositoryFirebase() {
        rooms = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(ROOM_REPOSITORY).addValueEventListener(this);
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public void createRoom(Room room) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(ROOM_REPOSITORY).updateChildren(Collections.<String, Object>singletonMap(room.getId(), room));
    }

    @Override
    public void deleteRoom(String roomId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(ROOM_REPOSITORY).child(roomId).removeValue();
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        ArrayList<Room> rooms = new ArrayList<>();
        for (DataSnapshot roomSnapshot : dataSnapshot.getChildren()) {
            rooms.add(roomSnapshot.getValue(Room.class));
        }
        this.rooms = rooms;
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
